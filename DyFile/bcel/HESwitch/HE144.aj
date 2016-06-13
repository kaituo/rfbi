package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE144 {
	before(org.apache.bcel.generic.GETFIELD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.GETFIELD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}