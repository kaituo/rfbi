package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE343 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDUP_X1(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDUP_X1", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}