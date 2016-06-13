package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE18 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIREM(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}