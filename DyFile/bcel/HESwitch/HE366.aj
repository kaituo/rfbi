package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE366 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitI2F(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitI2F", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}