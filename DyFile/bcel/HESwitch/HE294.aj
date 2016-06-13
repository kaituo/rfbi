package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE294 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitBALOAD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitBALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}