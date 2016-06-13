package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE361 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* _visitStackAccessor(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor._visitStackAccessor", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}