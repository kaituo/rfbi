package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE137 {
	before(org.apache.bcel.verifier.Verifier instance): execution(* doPass3b(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.Verifier.doPass3b", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}