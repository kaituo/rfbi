package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE145 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitSALOAD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitSALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}