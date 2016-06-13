package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE26 {
	before(org.apache.bcel.generic.DSTORE instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.DSTORE.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}