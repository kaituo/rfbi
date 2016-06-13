package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE210 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIFNONNULL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIFNONNULL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}