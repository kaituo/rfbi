package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE135 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFNEG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFNEG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}