package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE209 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLXOR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLXOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}