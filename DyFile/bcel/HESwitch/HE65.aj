package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE65 {
	before(org.apache.bcel.verifier.TransitiveHull instance): execution(* update(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.TransitiveHull.update", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}