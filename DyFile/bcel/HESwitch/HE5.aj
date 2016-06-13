package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE5 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitI2C(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitI2C", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}