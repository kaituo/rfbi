package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE242 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDUP(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDUP", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}