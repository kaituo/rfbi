package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE303 {
	before(org.apache.bcel.verifier.structurals.Subroutines instance): execution(* noRecursiveCalls(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.Subroutines.noRecursiveCalls", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}