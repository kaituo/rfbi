package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE165 {
	before(org.apache.bcel.generic.LSTORE instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LSTORE.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}