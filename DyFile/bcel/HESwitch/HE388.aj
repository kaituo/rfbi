package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE388 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitIFGE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitIFGE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}