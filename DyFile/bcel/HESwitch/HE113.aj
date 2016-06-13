package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE113 {
	before(org.apache.bcel.generic.ISTORE instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ISTORE.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}