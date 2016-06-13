package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE148 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitL2D(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitL2D", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}