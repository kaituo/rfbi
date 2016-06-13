package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE204 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIADD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIADD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}