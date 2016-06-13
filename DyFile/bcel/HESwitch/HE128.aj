package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE128 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitI2L(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitI2L", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}