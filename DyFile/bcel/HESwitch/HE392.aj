package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE392 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDNEG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDNEG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}