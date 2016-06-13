package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE283 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitINSTANCEOF(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitINSTANCEOF", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}