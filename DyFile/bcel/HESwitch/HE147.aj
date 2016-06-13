package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE147 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitBASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitBASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}