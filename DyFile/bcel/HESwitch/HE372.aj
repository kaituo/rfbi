package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE372 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}