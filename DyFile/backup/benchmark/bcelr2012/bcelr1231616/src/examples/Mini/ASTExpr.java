/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 *
 */
/* Generated By:JJTree: Do not edit this line. ASTExpr.java */
/* JJT: 0.3pre1 */

package Mini;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.IF_ICMPGE;
import org.apache.bcel.generic.IF_ICMPGT;
import org.apache.bcel.generic.IF_ICMPLE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;

/**
 * Represents arithmetic expressions such as `(a + 12 == b) OR c'.
 * The parse tree is built initially by the parser and modified, i.e.
 * compacted with `traverse()'. Each (Expr, Term, Factor) node
 * with kind == -1 is replaced which its successor node, which is
 * converted to type `Expr'
 *
 * A node with  kind == -1 denotes the fact that this expression
 * node has just one child branch and thus may be replaced by this
 * branch (or leaf) directly without altering the expression
 * semantics. Term and Factor nodes are used only to build the parse tree
 * obeying the aritmetical precedences (* stronger than +, etc.) and
 * are discarded in the first pass.
*/
public class ASTExpr extends SimpleNode 
implements MiniParserConstants, MiniParserTreeConstants, org.apache.bcel.Constants {
  protected int         kind=-1;    // Single twig to leaf?
  private   int         unop=-1;    // Special case: Unary operand applied
  protected ASTExpr[]   exprs;      // Sub expressions
  protected Environment env;        // Needed in all passes
  protected int         line, column;
  protected boolean     is_simple;  // true, if simple expression like `12 + f(a)'

 /* Not all children shall inherit this, exceptions are ASTIdent and ASTFunAppl, which
  * look up the type in the corresponding environment entry.
  */
  protected int         type = T_UNKNOWN;

  // Generated methods
  ASTExpr(int id) {
    super(id);
  }

  ASTExpr(MiniParser p, int id) {
    super(p, id);
  }

  public static Node jjtCreate(MiniParser p, int id) {
    return new ASTExpr(p, id);
  }

  ASTExpr(int line, int column, int id) {
    super(id);
    this.line   = line;
    this.column = column;
  }

  ASTExpr(int line, int column, int kind, int id) {
    this(line, column, id);
    this.kind = kind;
  }

  /* Special constructor, called from ASTTerm.traverse() and
   * ASTFactor.traverse(), when traverse()ing the parse tree replace
   * themselves with Expr nodes. 
   */
  ASTExpr(ASTExpr[] children, int kind, int line, int column) {
    this(line, column, kind, JJTEXPR);
    exprs = children;
  }

  /**
   * @return name of node, its kind and the number of children.
   */
  @Override
  public String toString() {
    String op="";
    int    len = (children != null)? children.length : 0;
    if(unop != -1) {
        op = tokenImage[unop];
    } else if(kind != -1) {
        op = tokenImage[kind];
    }

    return jjtNodeName[id] + "(" + op + ")[" + len + "]<" +
      TYPE_NAMES[type] + "> @" + line + ", " + column;
  }

  /**
   * Overrides SimpleNode.closeNode(). Overridden by some subclasses.
   *
   * Called by the parser when the construction of this node is finished.
   * Casts children Node[] to precise ASTExpr[] type.
   */
  @Override
  public void closeNode() {
    if(children != null) {
      exprs = new ASTExpr[children.length];
      System.arraycopy(children, 0, exprs, 0, children.length);
      children=null; // Throw away old reference
    }
  }

  /**
   * First pass
   * Overridden by subclasses. Traverse the whole parse tree recursively and
   * drop redundant nodes.
   */
  public ASTExpr traverse(Environment env) {
    this.env = env;

    if((kind == -1) && (unop == -1)) {
        return exprs[0].traverse(env);  // --> Replaced by successor
    } else {
      for(int i=0; i < exprs.length; i++) {
        exprs[i] = exprs[i].traverse(env); // References may change
    }
    
      return this;
    }
  }

  /** 
   * Second and third pass
   * @return type of expression
   * @param expected type
   */
  public int eval(int expected) {
    int child_type = T_UNKNOWN, t;
    
    is_simple = true;

    // Determine expected node type depending on used operator.
    if(unop != -1) {
      if(unop == MINUS) {
        child_type = type = T_INT;  // - 
    } else {
        child_type = type = T_BOOLEAN; // !
    }
    }
    else {
      // Compute expected type
      if((kind == PLUS) || (kind == MINUS) || (kind == MULT) ||
       (kind == MOD)  || (kind == DIV)) {
        child_type = type = T_INT;
    } else if((kind == AND) || (kind == OR)) {
        child_type = type = T_BOOLEAN;
    } else { // LEQ, GT, etc.
        child_type = T_INT;
        type       = T_BOOLEAN;
      }
    }

    // Get type of subexpressions
    for(int i=0; i < exprs.length; i++) {
      t = exprs[i].eval(child_type); 

      if(t != child_type) {
        MiniC.addError(exprs[i].getLine(), exprs[i].getColumn(),
                       "Expression has not expected type " + TYPE_NAMES[child_type] +
                       " but " + TYPE_NAMES[t] + ".");
    }

      is_simple = is_simple && exprs[i].isSimple();
    }

    return type;
  }

  private static final String toBool(String i) {
    return "(" + i + " != 0)";
  }

  private static final String toInt(String i) {
    return "((" + i + ")? 1 : 0)";
  }

  /**
   * Fourth pass, produce Java code.
   */  
  public void code(StringBuffer buf) {
    if(unop != -1) {
      exprs[0].code(buf);
      String top = ASTFunDecl.pop();
      if(unop == MINUS) {
        ASTFunDecl.push(buf, "-" + top);
    } else {
        ASTFunDecl.push(buf, "(" + top + " == 1)? 0 : 1)");
    }
    }
    else {
      exprs[0].code(buf);
      exprs[1].code(buf);
      String _body_int2 = ASTFunDecl.pop();
      String _body_int  = ASTFunDecl.pop();

      switch(kind) {
      case PLUS:  ASTFunDecl.push(buf, _body_int + " + " + _body_int2); break;
      case MINUS: ASTFunDecl.push(buf, _body_int + " - " + _body_int2); break;
      case MULT:  ASTFunDecl.push(buf, _body_int + " * " + _body_int2); break;
      case DIV:   ASTFunDecl.push(buf, _body_int + " / " + _body_int2); break;

      case AND:   ASTFunDecl.push(buf, toInt(toBool(_body_int) + " && " + 
              toBool(_body_int2))); break;
      case OR:    ASTFunDecl.push(buf, toInt(toBool(_body_int) + " || " +
              toBool(_body_int2))); break;

      case EQ:    ASTFunDecl.push(buf, toInt(_body_int + " == " + _body_int2));
        break;
      case LEQ:   ASTFunDecl.push(buf, toInt(_body_int + " <= " + _body_int2));
        break;
      case GEQ:   ASTFunDecl.push(buf, toInt(_body_int + " >= " + _body_int2));
        break;
      case NEQ:   ASTFunDecl.push(buf, toInt(_body_int + " != " + _body_int2));
        break;
      case LT:    ASTFunDecl.push(buf, toInt(_body_int + " < " + _body_int2));
        break;
      case GT:    ASTFunDecl.push(buf, toInt(_body_int + " > " + _body_int2));
        break;

      default: System.err.println("Ooops");
      }
    }
  }

  /**
   * Fifth pass, produce Java byte code.
   */
  public void byte_code(InstructionList il, MethodGen method, ConstantPoolGen cp) {
    exprs[0].byte_code(il, method, cp);

    if(unop != -1) { // Apply unary operand
      if(unop == MINUS) {
        il.append(InstructionConstants.INEG);
    } else { // == NOT
        il.append(new PUSH(cp, 1)); ASTFunDecl.push(); // Push TRUE
        il.append(InstructionConstants.IXOR); ASTFunDecl.pop();
      }
    }
    else { // Apply binary operand
      BranchHandle bh=null;

      exprs[1].byte_code(il, method, cp);

      switch(kind) {
      case PLUS:  il.append(InstructionConstants.IADD); ASTFunDecl.pop();  break;
      case MINUS: il.append(InstructionConstants.ISUB); ASTFunDecl.pop();  break;
      case MULT:  il.append(InstructionConstants.IMUL); ASTFunDecl.pop();  break;
      case DIV:   il.append(InstructionConstants.IDIV); ASTFunDecl.pop();  break;
      case AND:   il.append(InstructionConstants.IAND); ASTFunDecl.pop();  break;
      case OR:    il.append(InstructionConstants.IOR);  ASTFunDecl.pop();  break;

        /* Use negated operands */
      case EQ:    bh = il.append(new IF_ICMPNE(null)); ASTFunDecl.pop(2); break;
      case LEQ:   bh = il.append(new IF_ICMPGT(null)); ASTFunDecl.pop(2); break;
      case GEQ:   bh = il.append(new IF_ICMPLT(null)); ASTFunDecl.pop(2); break;
      case NEQ:   bh = il.append(new IF_ICMPEQ(null)); ASTFunDecl.pop(2); break;
      case LT:    bh = il.append(new IF_ICMPGE(null)); ASTFunDecl.pop(2); break;
      case GT:    bh = il.append(new IF_ICMPLE(null)); ASTFunDecl.pop(2); break;
      default: System.err.println("Ooops");
      }

      switch(kind) {
      case EQ: case LEQ: case GEQ: case NEQ: case LT: case GT:
        BranchHandle g;

        il.append(new PUSH(cp, 1));
        g = il.append(new GOTO(null));
        bh.setTarget(il.append(new PUSH(cp, 0)));
        g.setTarget(il.append(InstructionConstants.NOP)); // May be optimized away later
        ASTFunDecl.push();
        break;

      default: break;
      }
    }
  }

  public boolean isSimple()         { return is_simple; }
  public void setType(int type)     { this.type = type; }
  public int  getType()             { return type; }
  public void setKind(int kind)     { this.kind = kind; }
  public int  getKind()             { return kind; }
  public void setUnOp(int unop)     { this.unop = unop; }
  public int  getUnOp()             { return unop; }
  public void setLine(int line)     { this.line = line; }
  public int  getLine()             { return line; }
  public void setColumn(int column) { this.column = column; }
  public int  getColumn()           { return column; }
  public void setPosition(int line, int column) {
    this.line = line;
    this.column = column;
  }

  @Override
  public void dump(String prefix) {
    System.out.println(toString(prefix));

    if(exprs != null) {
        for(int i=0; i < exprs.length; ++i) {
            exprs[i].dump(prefix + " ");
        }
    }
  }
}