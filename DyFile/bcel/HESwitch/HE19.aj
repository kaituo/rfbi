package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE19 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitNEWARRAY(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitNEWARRAY", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}