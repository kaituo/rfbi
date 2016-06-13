package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE261 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitI2S(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitI2S", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}