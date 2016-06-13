package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE341 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDUP2_X2(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDUP2_X2", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}