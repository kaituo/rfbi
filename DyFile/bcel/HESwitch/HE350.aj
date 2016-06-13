package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE350 {
	before(org.apache.bcel.generic.LDC instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LDC.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}