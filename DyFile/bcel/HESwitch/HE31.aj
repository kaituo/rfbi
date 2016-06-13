package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE31 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLDIV(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}