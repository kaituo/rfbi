package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE117 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLDC(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLDC", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}