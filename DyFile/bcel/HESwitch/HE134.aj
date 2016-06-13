package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE134 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIRETURN(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}