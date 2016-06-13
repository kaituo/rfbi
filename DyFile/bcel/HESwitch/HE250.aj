package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE250 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLUSHR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLUSHR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}