package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE69 {
	before(org.apache.bcel.generic.FSTORE instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FSTORE.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}