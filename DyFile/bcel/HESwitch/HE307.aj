package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE307 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitPUTFIELD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitPUTFIELD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}