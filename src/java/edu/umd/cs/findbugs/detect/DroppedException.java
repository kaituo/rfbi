/*
 * FindBugs - Find bugs in Java programs
 * Copyright (C) 2003-2005, University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umd.cs.findbugs.detect;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantCP;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantInvokeDynamic;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Utility;

import edu.umass.cs.rfbi.cg.DECodeGenerator;
import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.BugAccumulator;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;
import edu.umd.cs.findbugs.LocalVariableAnnotation;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import edu.umd.cs.findbugs.SystemProperties;
import edu.umd.cs.findbugs.Token;
import edu.umd.cs.findbugs.Tokenizer;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.Hierarchy;
import edu.umd.cs.findbugs.ba.SourceFile;
import edu.umd.cs.findbugs.ba.SourceFinder;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.charsets.UTF8;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;
import edu.umd.cs.findbugs.internalAnnotations.DottedClassName;
import edu.umd.cs.findbugs.internalAnnotations.SlashedClassName;
import edu.umd.cs.findbugs.visitclass.DismantleBytecode;
import edu.umd.cs.findbugs.visitclass.PreorderVisitor;

public class DroppedException extends PreorderVisitor implements Detector {
    private static final boolean DEBUG = SystemProperties.getBoolean("de.debug");

    private static final boolean LOOK_IN_SOURCE_TO_FIND_COMMENTED_CATCH_BLOCKS = SystemProperties
            .getBoolean("findbugs.de.comment");

    Set<String> causes = new HashSet<String>();

    Set<String> checkedCauses = new HashSet<String>();

    private final BugReporter bugReporter;
    private final BugAccumulator bugAccumulator;

    private ClassContext classContext;

    public DroppedException(BugReporter bugReporter) {
        this.bugReporter = bugReporter;
        this.bugAccumulator = new BugAccumulator(bugReporter);
        if (DEBUG) {
            System.out.println("Dropped Exception debugging turned on");
        }
    }

    @Override
    public void visitClassContext(ClassContext classContext) {
        this.classContext = classContext;
        classContext.getJavaClass().accept(this);
        bugAccumulator.reportAccumulatedBugs();

    }

    @Override
    public void report() {
    }

    boolean isChecked(String c) {
        if (!causes.add(c)) {
            return checkedCauses.contains(c);
        }
        try {
            if (Hierarchy.isSubtype(c, "java.lang.Exception") && !Hierarchy.isSubtype(c, "java.lang.RuntimeException")) {
                checkedCauses.add(c);
                return true;
            }
        } catch (ClassNotFoundException e) {
            bugReporter.reportMissingClass(e);
        }
        return false;
    }

    private int getUnsignedShort(byte[] a, int i) {
        return asUnsignedByte(a[i]) << 8 | asUnsignedByte(a[i + 1]);
    }

    @Override
    public void visit(Code obj) {

        CodeException[] exp = obj.getExceptionTable();
        LineNumberTable lineNumbers = obj.getLineNumberTable();
        if (exp == null) {
            return;
        }
        byte[] code = obj.getCode();

        for (CodeException aExp : exp) {
            int handled = aExp.getHandlerPC();
            int start = aExp.getStartPC();
            int end = aExp.getEndPC();
            int cause = aExp.getCatchType();
            boolean exitInTryBlock = false;
            if (DEBUG) {
                System.out.println("start = " + start + ", end = " + end + ", codeLength = " + code.length + ", handled = "
                        + handled);
            }

            for (int j = start; j <= end && j < code.length;) {
                int opcode = asUnsignedByte(code[j]);
                if (NO_OF_OPERANDS[opcode] < 0) {
                    exitInTryBlock = true;
                    break;
                }
                j += 1 + NO_OF_OPERANDS[opcode];
                if (opcode >= IRETURN && opcode <= RETURN || opcode >= IFEQ && opcode <= GOTO && (opcode != GOTO || j < end)) {
                    exitInTryBlock = true;
                    if (DEBUG) {
                        System.out.println("\texit: " + opcode + " in " + getFullyQualifiedMethodName());
                    }
                    break;
                }

            }

            if (exitInTryBlock) {
                if (DEBUG) {
                    System.out.println("Exit in try block");
                }
                continue;
            }
            if (handled < 5) {
                continue;
            }
            @DottedClassName String causeName;
            if (cause == 0) {
                causeName = "java.lang.Throwable";
            } else {
                causeName = Utility.compactClassName(getConstantPool().getConstantString(cause, CONSTANT_Class), false);
                if (!isChecked(causeName)) {
                    continue;
                }
            }

            int jumpAtEnd = 0;
            if (end < code.length && asUnsignedByte(code[end]) == GOTO) {
                jumpAtEnd = getUnsignedShort(code, end + 1);
                if (jumpAtEnd < handled) {
                    jumpAtEnd = 0;
                }
            }

            int opcode = asUnsignedByte(code[handled]);
            int afterHandler = 0;
            if (DEBUG) {
                System.out.println("DE:\topcode is " + OPCODE_NAMES[opcode] + ", " + asUnsignedByte(code[handled + 1]));
            }
            boolean drops = false;
            boolean startsWithASTORE03 = opcode >= ASTORE_0 && opcode <= ASTORE_3;
            if (startsWithASTORE03 && asUnsignedByte(code[handled + 1]) == RETURN) {
                if (DEBUG) {
                    System.out.println("Drop 1");
                }
                drops = true;
                afterHandler = handled + 1;
            }
            if (handled + 2 < code.length && opcode == ASTORE && asUnsignedByte(code[handled + 2]) == RETURN) {
                drops = true;
                afterHandler = handled + 2;
                if (DEBUG) {
                    System.out.println("Drop 2");
                }
            }
            if (handled + 3 < code.length && !exitInTryBlock) {
                if (DEBUG) {
                    System.out.println("DE: checking for jumps");
                }
                if (startsWithASTORE03 && asUnsignedByte(code[handled - 3]) == GOTO) {
                    int offsetBefore = getUnsignedShort(code, handled - 2);
                    if (DEBUG) {
                        System.out.println("offset before = " + offsetBefore);
                    }
                    if (offsetBefore == 4) {
                        drops = true;
                        afterHandler = handled + 1;
                        if (DEBUG) {
                            System.out.println("Drop 3");
                        }
                    }
                }
                if (opcode == ASTORE && asUnsignedByte(code[handled - 3]) == GOTO) {
                    int offsetBefore = getUnsignedShort(code, handled - 2);
                    if (offsetBefore == 5) {
                        drops = true;
                        afterHandler = handled + 2;
                        if (DEBUG) {
                            System.out.println("Drop 4");
                        }
                    }
                }
                if (startsWithASTORE03 && asUnsignedByte(code[handled + 1]) == GOTO && asUnsignedByte(code[handled - 3]) == GOTO) {
                    int offsetBefore = getUnsignedShort(code, handled - 2);
                    int offsetAfter = getUnsignedShort(code, handled + 2);

                    if (offsetAfter > 0 && offsetAfter + 4 == offsetBefore) {
                        drops = true;
                        afterHandler = handled + 4;
                        if (DEBUG) {
                            System.out.println("Drop 5");
                        }
                    }
                }

                if (opcode == ASTORE && asUnsignedByte(code[handled + 2]) == GOTO && asUnsignedByte(code[handled - 3]) == GOTO) {
                    int offsetBefore = getUnsignedShort(code, handled - 2);
                    int offsetAfter = getUnsignedShort(code, handled + 3);

                    if (offsetAfter > 0 && offsetAfter + 5 == offsetBefore) {
                        drops = true;
                        afterHandler = handled + 5;
                        if (DEBUG) {
                            System.out.println("Drop 6");
                        }
                    }
                }

            }

            boolean multiLineHandler = false;
            if (DEBUG) {
                System.out.println("afterHandler = " + afterHandler + ", handled = " + handled);
            }
            if (afterHandler > handled && lineNumbers != null) {
                int startHandlerLinenumber = lineNumbers.getSourceLine(handled);

                int endHandlerLinenumber = getNextExecutableLineNumber(lineNumbers, afterHandler) - 1;
                if (DEBUG) {
                    System.out.println("Handler in lines " + startHandlerLinenumber + "-" + endHandlerLinenumber);
                }
                if (endHandlerLinenumber > startHandlerLinenumber) {
                    multiLineHandler = true;
                    if (DEBUG) {
                        System.out.println("Multiline handler");
                    }
                }
            }

            if (end - start >= 4 && drops && !"java.lang.InterruptedException".equals(causeName)
                    && !"java.lang.CloneNotSupportedException".equals(causeName)) {
                int priority = NORMAL_PRIORITY;
                if (exitInTryBlock) {
                    priority++;
                }
                if (end - start == 4) {
                    priority++;
                }
                SourceLineAnnotation srcLine = SourceLineAnnotation.fromVisitedInstruction(this.classContext, this, handled);
                if (srcLine != null && LOOK_IN_SOURCE_TO_FIND_COMMENTED_CATCH_BLOCKS) {
                    if (catchBlockHasComment(srcLine)) {
                        return;
                    } else {
                        priority++;
                    }
                } else {
                    // can't look at source
                    if (lineNumbers == null || multiLineHandler) {
                        priority += 2;
                    }
                }
                if ("java.lang.Error".equals(causeName) || "java.lang.Exception".equals(causeName) || "java.lang.Throwable".equals(causeName)
                        || "java.lang.RuntimeException".equals(causeName)) {
                    priority--;
                    if (end - start > 30) {
                        priority--;
                    }
                }

                int register = -1;
                if (startsWithASTORE03) {
                    register = opcode - ASTORE_0;
                } else if (opcode == ASTORE) {
                    register =  asUnsignedByte(code[handled + 1]);
                }
                if (register >= 0) {
                    LocalVariableAnnotation lva = LocalVariableAnnotation.getLocalVariableAnnotation(getMethod(), register, handled+2, handled+1);
                    String name = lva.getName();
                    if (DEBUG) {
                        System.out.println("Name: " + name);
                    }
                    if (name.startsWith("ignore") || name.startsWith("cant")) {
                        continue;
                    }
                }


                if (DEBUG) {
                    System.out.println("Priority is " + priority);
                }
                if (priority > LOW_PRIORITY) {
                    return;
                }
                if (priority < HIGH_PRIORITY) {
                    priority = HIGH_PRIORITY;
                }
                if (DEBUG) {
                    System.out.println("reporting warning");
                }

                BugInstance bugInstance = new BugInstance(this, exitInTryBlock ? "DE_MIGHT_DROP" : "DE_MIGHT_IGNORE", priority)
                .addClassAndMethod(this);
                bugInstance.addClass(causeName).describe("CLASS_EXCEPTION");
                bugInstance.addSourceLine(srcLine);
                bugAccumulator.accumulateBug(bugInstance, srcLine);
                // start code generation
                if(Config.getInstance().getProperty("de.enabled").equals("false")) {
                    return;
                }
                String bugLoc = srcLine.toString();
                analyzeMethodCall(code, start, end, causeName, bugLoc);
            }
        }
    }

    void analyzeMethodCall(byte[] codeBytes, int startPC, int endPC, String causeName, String bugLoc) {

        DataInputStream byteStream = new DataInputStream(new ByteArrayInputStream(codeBytes));
        String classConstantOperand, nameConstantOperand, sigConstantOperand;
        long skipped = 0;
        try {
            skipped = byteStream.skip(startPC);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if((int)skipped<startPC) {
                if (DEBUG) {
                    System.out.println("cannot skip enough amount of bytes.");
                }
                return;
            }
        }
        try {
            for (int i = startPC; i < endPC;) {

                classConstantOperand = nameConstantOperand = sigConstantOperand = SlashedClassName.NOT_AVAILABLE;
                int opcode = byteStream.readUnsignedByte();
                i++;
                int byteStreamArgCount = NO_OF_OPERANDS[opcode];
                if (byteStreamArgCount == UNPREDICTABLE) {

                    if (opcode == LOOKUPSWITCH) {
                        int pad = 4 - (i & 3);
                        if (pad == 4) {
                            pad = 0;
                        }
                        int count = pad;
                        while (count > 0) {
                            count -= byteStream.skipBytes(count);
                        }
                        i += pad;
                        byteStream.readInt();

                        i += 4;
                        int npairs = byteStream.readInt();
                        i += 4;

                        for (int o = 0; o < npairs; o++) {
                            byteStream.readInt();
                            byteStream.readInt();
                            i += 8;
                        }

                    } else if (opcode == TABLESWITCH) {
                        int pad = 4 - (i & 3);
                        if (pad == 4) {
                            pad = 0;
                        }
                        int count = pad;
                        while (count > 0) {
                            count -= byteStream.skipBytes(count);
                        }
                        i += pad;
                        byteStream.readInt();

                        i += 4;
                        int switchLow = byteStream.readInt();
                        i += 4;
                        int switchHigh = byteStream.readInt();
                        i += 4;
                        int npairs = switchHigh - switchLow + 1;

                        for (int o = 0; o < npairs; o++) {
                            byteStream.readInt();
                            i += 4;
                        }
                    } else if (opcode == WIDE) {
                        opcode = byteStream.readUnsignedByte();
                        i++;
                        switch (opcode) {
                        case ILOAD:
                        case FLOAD:
                        case ALOAD:
                        case LLOAD:
                        case DLOAD:
                        case ISTORE:
                        case FSTORE:
                        case ASTORE:
                        case LSTORE:
                        case DSTORE:
                        case RET:
                            byteStream.readUnsignedShort();
                            i += 2;
                            break;
                        case IINC:
                            byteStream.readUnsignedShort();
                            i += 2;
                            byteStream.readShort();
                            i += 2;
                            break;
                        default:
                            throw new IllegalStateException(String.format("bad wide bytecode %d: %s" , opcode, OPCODE_NAMES[opcode]));
                        }
                    } else {
                        throw new IllegalStateException(String.format("bad unpredicatable bytecode %d: %s" , opcode, OPCODE_NAMES[opcode]));
                    }
                } else {
                    if (byteStreamArgCount < 0) {
                        throw new IllegalStateException(String.format("bad length for bytecode %d: %s" , opcode, OPCODE_NAMES[opcode]));
                    }
                    for (int k = 0; k < TYPE_OF_OPERANDS[opcode].length; k++) {

                        int v;
                        int t = TYPE_OF_OPERANDS[opcode][k];
                        int m = DismantleBytecode.MEANING_OF_OPERANDS[opcode][k];
                        boolean unsigned = (m == DismantleBytecode.M_CP || m == DismantleBytecode.M_R || m == DismantleBytecode.M_UINT);
                        switch (t) {
                        case T_BYTE:
                            if (unsigned) {
                                v = byteStream.readUnsignedByte();
                            } else {
                                v = byteStream.readByte();
                            }
                            /*
                             * System.out.print("Read byte " + v);
                             * System.out.println(" with meaning" + m);
                             */
                            i++;
                            break;
                        case T_SHORT:
                            if (unsigned) {
                                v = byteStream.readUnsignedShort();
                            } else {
                                v = byteStream.readShort();
                            }
                            i += 2;
                            break;
                        case T_INT:
                            v = byteStream.readInt();
                            i += 4;
                            break;
                        default:
                            throw new IllegalStateException();
                        }
                        switch (m) {
                        case DismantleBytecode.M_BR:
                        case DismantleBytecode.M_R:
                        case DismantleBytecode.M_UINT:
                        case DismantleBytecode.M_INT:
                        case DismantleBytecode.M_PAD:
                            break;
                        case DismantleBytecode.M_CP:
                            Constant constantRefOperand = getConstantPool().getConstant(v);
                            if (constantRefOperand instanceof ConstantCP) {
                                ConstantCP cp = (ConstantCP) constantRefOperand;
                                ConstantClass clazz = (ConstantClass) getConstantPool().getConstant(cp.getClassIndex());
                                classConstantOperand = getStringFromIndex(clazz.getNameIndex());

                                ConstantNameAndType sig = (ConstantNameAndType) getConstantPool().getConstant(
                                        cp.getNameAndTypeIndex());
                                nameConstantOperand = getStringFromIndex(sig.getNameIndex());
                                sigConstantOperand = getStringFromIndex(sig.getSignatureIndex());
                            } else if (constantRefOperand instanceof ConstantInvokeDynamic) {
                                ConstantInvokeDynamic id = (ConstantInvokeDynamic) constantRefOperand;
                                ConstantNameAndType sig = (ConstantNameAndType) getConstantPool().getConstant(
                                        id.getNameAndTypeIndex());
                                nameConstantOperand = getStringFromIndex(sig.getNameIndex());
                                sigConstantOperand = getStringFromIndex(sig.getSignatureIndex());
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpecting meaning " + m);
                        }
                    }

                }
                switch (opcode) {
                case Constants.INVOKESTATIC:
                case Constants.INVOKEVIRTUAL:
                case Constants.INVOKEINTERFACE:
                case Constants.INVOKESPECIAL:
                    if (nameConstantOperand == SlashedClassName.NOT_AVAILABLE || classConstantOperand == SlashedClassName.NOT_AVAILABLE
                    || sigConstantOperand == SlashedClassName.NOT_AVAILABLE) {
                        throw new IllegalStateException("method info not available");
                    }
                    MethodDescriptor called = DescriptorFactory.instance().getMethodDescriptor(classConstantOperand, nameConstantOperand,
                            sigConstantOperand, opcode == INVOKESTATIC);
                    XMethod calledXMethod = XFactory.createXMethod(called);
                    DECodeGenerator.getInstance().generateAspectJ(calledXMethod, causeName, bugLoc);
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            AnalysisContext.logError("Error while dismantling bytecode", e);
            assert false;
        }

        try {
            byteStream.close();
        } catch (IOException e) {
            assert false;
        }
    }

    private int getNextExecutableLineNumber(LineNumberTable linenumbers, int PC) {
        LineNumber[] entries = linenumbers.getLineNumberTable();
        int beforePC = 0;
        int i = 0;
        for (; i < entries.length && entries[i].getStartPC() < PC; i++) {
            int line = entries[i].getLineNumber();
            if (line > beforePC) {
                beforePC = line;
            }
        }

        if (i < entries.length) {
            int secondChoice = entries[i].getLineNumber();
            for (; i < entries.length; i++) {
                int line = entries[i].getLineNumber();
                if (line > beforePC) {
                    return line;
                }
            }
            return secondChoice;
        } else {
            return entries[entries.length - 1].getLineNumber();
        }
    }

    private static final int START = 0;

    private static final int CATCH = 1;

    private static final int OPEN_PAREN = 2;

    private static final int CLOSE_PAREN = 3;

    private static final int OPEN_BRACE = 4;

    /**
     * Maximum number of lines we look backwards to find the "catch" keyword.
     * Looking backwards is necessary when the indentation style puts the open
     * brace on a different line from the catch clause.
     */
    private static final int NUM_CONTEXT_LINES = 3;

    /**
     * The number of lines that we'll scan to look at the source for a catch
     * block.
     */
    private static final int MAX_LINES = 7;

    /**
     * Analyze a class's source code to see if there is a comment (or other
     * text) in a catch block we have marked as dropping an exception.
     *
     * @return true if there is a comment in the catch block, false if not (or
     *         if we can't tell)
     */
    private boolean catchBlockHasComment(SourceLineAnnotation srcLine) {
        if (!LOOK_IN_SOURCE_TO_FIND_COMMENTED_CATCH_BLOCKS) {
            return false;
        }

        SourceFinder sourceFinder = AnalysisContext.currentAnalysisContext().getSourceFinder();
        try {
            SourceFile sourceFile = sourceFinder.findSourceFile(srcLine.getPackageName(), srcLine.getSourceFile());
            int startLine = srcLine.getStartLine();

            int scanStartLine = startLine - NUM_CONTEXT_LINES;
            if (scanStartLine < 1) {
                scanStartLine = 1;
            }

            int offset = sourceFile.getLineOffset(scanStartLine - 1);
            if (offset < 0)
            {
                return false; // Source file has changed?
            }
            Tokenizer tokenizer = new Tokenizer(UTF8.reader(sourceFile.getInputStreamFromOffset(offset)));

            // Read the tokens into an ArrayList,
            // keeping track of where the catch block is reported
            // to start
            ArrayList<Token> tokenList = new ArrayList<Token>(40);
            int eolOfCatchBlockStart = -1;
            for (int line = scanStartLine; line < scanStartLine + MAX_LINES;) {
                Token token = tokenizer.next();
                int kind = token.getKind();
                if (kind == Token.EOF) {
                    break;
                }

                if (kind == Token.EOL) {
                    if (line == startLine) {
                        eolOfCatchBlockStart = tokenList.size();
                    }
                    ++line;
                }

                tokenList.add(token);
            }

            if (eolOfCatchBlockStart < 0)
            {
                return false; // Couldn't scan line reported as start of catch
                // block
            }

            // Starting at the end of the line reported as the start of the
            // catch block,
            // scan backwards for the token "catch".
            ListIterator<Token> iter = tokenList.listIterator(eolOfCatchBlockStart);
            boolean foundCatch = false;

            while (iter.hasPrevious()) {
                Token token = iter.previous();
                if (token.getKind() == Token.WORD && "catch".equals(token.getLexeme())) {
                    foundCatch = true;
                    break;
                }
            }

            if (!foundCatch)
            {
                return false; // Couldn't find "catch" keyword
            }

            // Scan forward from the "catch" keyword to see what text
            // is in the handler block. If the block is non-empty,
            // then we suppress the warning (on the theory that the
            // programmer has indicated that there is a good reason
            // that the exception is ignored).
            boolean done = false;
            int numLines = 0;
            int state = START;
            int level = 0;
            do {
                if (!iter.hasNext()) {
                    break;
                }

                Token token = iter.next();
                int type = token.getKind();
                String value = token.getLexeme();

                switch (type) {
                case Token.EOL:
                    if (DEBUG) {
                        System.out.println("Saw token: [EOL]");
                    }
                    ++numLines;
                    if (numLines >= MAX_LINES) {
                        done = true;
                    }
                    break;
                default:
                    if (DEBUG) {
                        System.out.println("Got token: " + value);
                    }
                    switch (state) {
                    case START:
                        if ("catch".equals(value)) {
                            state = CATCH;
                        }
                        break;
                    case CATCH:
                        if ("(".equals(value)) {
                            state = OPEN_PAREN;
                        }
                        break;
                    case OPEN_PAREN:
                        if (")".equals(value)) {
                            if (level == 0) {
                                state = CLOSE_PAREN;
                            } else {
                                --level;
                            }
                        } else if ("(".equals(value)) {
                            ++level;
                        }
                        break;
                    case CLOSE_PAREN:
                        if ("{".equals(value)) {
                            state = OPEN_BRACE;
                        }
                        break;
                    case OPEN_BRACE:
                        boolean closeBrace = "}".equals(value);
                        if (DEBUG && !closeBrace) {
                            System.out.println("Found a comment in catch block: " + value);
                        }
                        return !closeBrace;
                    }
                    break;
                }
            } while (!done);
        } catch (IOException e) {
            // Ignored; we'll just assume there is no comment

        }
        return false;
    }
}

