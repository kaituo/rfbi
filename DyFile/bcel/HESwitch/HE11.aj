package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE11 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitPOP2(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitPOP2", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}