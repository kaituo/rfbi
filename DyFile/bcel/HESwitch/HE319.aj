package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE319 {
	before(org.apache.bcel.verifier.structurals.Subroutines instance): execution(* getSubroutine(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.Subroutines.getSubroutine", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}