package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE232 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitCHECKCAST(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitCHECKCAST", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}