package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE40 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* referenceTypeIsInitialized(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.referenceTypeIsInitialized", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}