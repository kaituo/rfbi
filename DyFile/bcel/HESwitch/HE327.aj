package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE327 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFSUB(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFSUB", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}