package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE72 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIXOR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIXOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}