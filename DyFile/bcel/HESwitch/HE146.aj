package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE146 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitINEG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitINEG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}