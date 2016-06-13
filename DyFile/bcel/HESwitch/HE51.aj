package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE51 {
	before(org.apache.bcel.generic.DLOAD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.DLOAD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}