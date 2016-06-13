package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE236 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFREM(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}