package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE222 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitAASTORE(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitAASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}