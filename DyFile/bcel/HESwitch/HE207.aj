package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE207 {
	before(org.apache.bcel.generic.ILOAD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ILOAD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}