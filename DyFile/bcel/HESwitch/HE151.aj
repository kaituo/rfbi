package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE151 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDCMPG(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDCMPG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}