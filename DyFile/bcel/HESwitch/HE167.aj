package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE167 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFCMPL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFCMPL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}