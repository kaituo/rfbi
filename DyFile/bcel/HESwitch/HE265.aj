package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE265 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLNEG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLNEG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}