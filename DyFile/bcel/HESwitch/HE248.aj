package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE248 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFCMPG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFCMPG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}