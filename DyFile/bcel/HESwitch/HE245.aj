package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE245 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFRETURN(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}