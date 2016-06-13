package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE354 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitGETFIELD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitGETFIELD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}