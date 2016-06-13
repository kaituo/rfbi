package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE396 {
	before(org.apache.bcel.verifier.structurals.Subroutines instance): execution(* subroutineOf(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.Subroutines.subroutineOf", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}