package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE247 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDCMPL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDCMPL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}