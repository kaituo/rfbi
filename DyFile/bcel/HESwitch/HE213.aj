package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE213 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIMUL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}