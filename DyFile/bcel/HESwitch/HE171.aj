package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE171 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitCASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitCASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}