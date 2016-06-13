package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE252 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitPUTSTATIC(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitPUTSTATIC", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}