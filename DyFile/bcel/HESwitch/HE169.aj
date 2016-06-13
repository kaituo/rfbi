package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE169 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitMONITOREXIT(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitMONITOREXIT", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}