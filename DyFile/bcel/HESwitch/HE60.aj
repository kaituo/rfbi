package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE60 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIDIV(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}