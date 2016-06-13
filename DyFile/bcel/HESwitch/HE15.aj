package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE15 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIF_ICMPGE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIF_ICMPGE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}