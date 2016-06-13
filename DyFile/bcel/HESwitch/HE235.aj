package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE235 {
	before(org.apache.bcel.verifier.Verifier instance): execution(* doPass3a(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.Verifier.doPass3a", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}