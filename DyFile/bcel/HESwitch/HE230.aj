package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE230 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitDADD(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitDADD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}