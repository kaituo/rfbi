package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE347 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDRETURN(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}