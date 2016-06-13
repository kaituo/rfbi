package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE4 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitISHR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitISHR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}