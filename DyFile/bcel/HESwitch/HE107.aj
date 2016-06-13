package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE107 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitATHROW(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitATHROW", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}