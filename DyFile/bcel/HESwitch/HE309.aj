package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE309 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFALOAD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}