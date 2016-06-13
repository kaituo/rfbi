package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE25 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIFEQ(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIFEQ", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}