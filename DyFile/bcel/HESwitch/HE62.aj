package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE62 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitINVOKEINTERFACE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitINVOKEINTERFACE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}