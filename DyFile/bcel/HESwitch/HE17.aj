package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE17 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIF_ICMPLE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIF_ICMPLE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}