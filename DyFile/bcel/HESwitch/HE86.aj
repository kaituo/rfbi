package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE86 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLREM(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}