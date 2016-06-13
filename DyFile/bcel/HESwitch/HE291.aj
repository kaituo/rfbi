package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE291 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitStoreInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitStoreInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}