package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE44 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDALOAD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}