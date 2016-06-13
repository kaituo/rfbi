package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE233 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitD2F(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitD2F", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}