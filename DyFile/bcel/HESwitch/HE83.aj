package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE83 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitReturnInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitReturnInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}