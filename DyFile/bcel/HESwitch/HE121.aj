package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE121 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLSHL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLSHL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}