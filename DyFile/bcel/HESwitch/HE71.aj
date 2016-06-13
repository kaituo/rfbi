package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE71 {
	before(org.apache.bcel.verifier.structurals.LocalVariables instance): execution(* set(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.LocalVariables.set", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}