package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE253 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIUSHR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIUSHR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}