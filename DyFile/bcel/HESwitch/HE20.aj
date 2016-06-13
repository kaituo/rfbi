package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE20 {
	before(org.apache.bcel.generic.MULTIANEWARRAY instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MULTIANEWARRAY.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}