package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE41 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFDIV(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}