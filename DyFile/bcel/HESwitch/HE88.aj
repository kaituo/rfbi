package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE88 {
	before(org.apache.bcel.generic.GETSTATIC instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.GETSTATIC.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}