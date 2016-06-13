package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE23 {
	before(org.apache.bcel.generic.ALOAD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ALOAD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}