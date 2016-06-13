package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE402 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDMUL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}