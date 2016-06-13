package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE122 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFieldInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFieldInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}