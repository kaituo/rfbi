package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE37 {
	before(org.apache.bcel.generic.ANEWARRAY instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ANEWARRAY.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}