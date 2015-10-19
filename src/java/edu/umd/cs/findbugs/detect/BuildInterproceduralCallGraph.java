/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2003-2008 University of Maryland
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

import java.util.Set;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Method;

import edu.umass.cs.rfbi.cg.SwitchCG;
import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.NonReportingDetector;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.ba.jsr305.Analysis;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;

/**
 * Build the interprocedural call graph.
 *
 * NOTE: at the present time, this facility is only used to find relevant type
 * qualifiers. It could become a more general-purpose facility if there were a
 * need.
 *
 * @author David Hovemeyer
 */
public class BuildInterproceduralCallGraph extends BytecodeScanningDetector implements NonReportingDetector {

    private InterproceduralCallGraph callGraph;

    private InterproceduralCallGraphVertex currentVertex;

    int count = 0;

    /**
     * Constructor.
     *
     * @param bugReporter
     *            the BugReporter to use
     */
    public BuildInterproceduralCallGraph(BugReporter bugReporter) {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        callGraph = new InterproceduralCallGraph();
    }

    @Override
    public void visitClassContext(ClassContext classContext) {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        super.visitClassContext(classContext);
    }

    @Override
    public void visitMethod(Method obj) {
        currentVertex = findVertex(getXMethod());
        super.visitMethod(obj);
    }

    @Override
    public void sawOpcode(int seen) {
        switch (seen) {
        case Constants.INVOKESTATIC:
        case Constants.INVOKEVIRTUAL:
        case Constants.INVOKEINTERFACE:
        case Constants.INVOKESPECIAL:
            count++;
            MethodDescriptor called = getMethodDescriptorOperand();
            XMethod calledXMethod = XFactory.createXMethod(called);
            InterproceduralCallGraphVertex calledVertex = findVertex(calledXMethod);
            callGraph.createEdge(currentVertex, calledVertex);
            addEdges4Subtypes(called, seen); // Kaituo
            break;
        default:
            break;
        }
    }

    /**
     * m
          ...
          invoke xxx T.m2

        ==>

        foreach t in T.getSubTypes():
            t.getMethod(m2).addPossibleCaller(m)

     * @param called
     * @param isStatic
     * @author Kaituo
     */
    private void addEdges4Subtypes(MethodDescriptor called, int seen) {
        // Only invokevirtual and invokeinterface has dynamic calls (subtype is possible)
        if(seen==Constants.INVOKESTATIC || seen==Constants.INVOKESPECIAL) {
            return;
        }
        ClassDescriptor calledClass = called.getClassDescriptor();
        // if the class is an array like "[Ljava.lang.Object", then we are not interested to continue
        if(calledClass.isArray()) {
            return;
        }

        Subtypes2 subtypes2 = AnalysisContext.currentAnalysisContext().getSubtypes2();

        try {
            Set<ClassDescriptor> mySubtypes = subtypes2.getSubtypes(calledClass);
            for (ClassDescriptor c : mySubtypes) {
                if (c.equals(getClassDescriptor())) {
                    continue;
                }
                // the last argument is false because this method cannot be static
                MethodDescriptor called4Subtype = DescriptorFactory.instance().getMethodDescriptor(c.getClassName(), called.getName(),
                        called.getSignature(), false);
                XMethod called4SubtypeXMethod = XFactory.createXMethod(called4Subtype);
                InterproceduralCallGraphVertex called4SubtypeVertex = findVertex(called4SubtypeXMethod);
                callGraph.createEdge(currentVertex, called4SubtypeVertex);
            }
        } catch (ClassNotFoundException e) {
            AnalysisContext.logError("Error while create call graph edges for " + called.toString());
            assert false;
        }

    }

    /**
     * Find the InterproceduralCallGraphVertex for given XMethod.
     *
     * @param xmethod
     *            an XMethod
     * @return the XMethod's InterproceduralCallGraphVertex
     */
    private InterproceduralCallGraphVertex findVertex(XMethod xmethod) {
        InterproceduralCallGraphVertex vertex;
        vertex = callGraph.lookupVertex(xmethod.getMethodDescriptor());
        if (vertex == null) {
            vertex = new InterproceduralCallGraphVertex();
            vertex.setXmethod(xmethod);
            callGraph.addVertex(vertex);
        }
        return vertex;
    }

    @Override
    public void report() {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        Global.getAnalysisCache().eagerlyPutDatabase(InterproceduralCallGraph.class, callGraph);
        if(Boolean.parseBoolean(Config.getInstance().getProperty("switch.enabled"))) {
            System.out.println("BuildInterprocedural's sawOpCode has been called " + count);
            SwitchCG scg = new SwitchCG();
            scg.generateAllSwitchAspects();
            //TraceWriter.writeState(callGraph);
        }
    }
}
