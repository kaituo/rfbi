package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE311 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLoadInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLoadInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}