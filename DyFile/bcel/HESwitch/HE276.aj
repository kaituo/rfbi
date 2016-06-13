package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE276 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* indexOfInt(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.indexOfInt", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}