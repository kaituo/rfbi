package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE211 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitF2I(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitF2I", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}