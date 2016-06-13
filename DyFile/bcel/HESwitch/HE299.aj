package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE299 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDREM(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}