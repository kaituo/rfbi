package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE393 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIFNULL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIFNULL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}