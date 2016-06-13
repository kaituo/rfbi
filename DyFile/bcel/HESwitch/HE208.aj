package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE208 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIOR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}