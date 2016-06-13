package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE75 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}