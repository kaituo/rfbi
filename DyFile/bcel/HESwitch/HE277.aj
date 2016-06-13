package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE277 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* arrayrefOfArrayType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.arrayrefOfArrayType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}