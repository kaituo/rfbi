package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE184 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLDC2_W(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLDC2_W", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}