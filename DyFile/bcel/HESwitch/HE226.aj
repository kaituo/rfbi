package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE226 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLRETURN(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}