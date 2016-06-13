package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE292 {
	before(org.apache.bcel.generic.IINC instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.IINC.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}