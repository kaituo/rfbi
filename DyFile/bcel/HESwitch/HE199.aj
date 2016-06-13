package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE199 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLOR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}