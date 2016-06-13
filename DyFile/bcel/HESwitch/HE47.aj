package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE47 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* valueOfInt(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.valueOfInt", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}