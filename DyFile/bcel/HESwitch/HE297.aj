package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE297 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDDIV(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}