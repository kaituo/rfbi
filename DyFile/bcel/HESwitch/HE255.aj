package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE255 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitFASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitFASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}