package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE305 {
	before(org.apache.bcel.generic.LOOKUPSWITCH instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LOOKUPSWITCH.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}