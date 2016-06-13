package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE348 {
	before(org.apache.bcel.generic.INVOKESTATIC instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.INVOKESTATIC.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}