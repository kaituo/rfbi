package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE156 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitI2D(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitI2D", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}