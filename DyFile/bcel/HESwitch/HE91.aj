package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE91 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIF_ACMPNE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIF_ACMPNE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}