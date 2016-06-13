package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE258 {
	before(org.apache.bcel.generic.LDC2_W instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LDC2_W.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}