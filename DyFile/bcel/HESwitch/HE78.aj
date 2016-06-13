package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE78 {
	before(org.apache.bcel.generic.FLOAD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FLOAD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}