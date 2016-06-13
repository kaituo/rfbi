package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE295 {
	before(org.apache.bcel.generic.PUTFIELD instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.PUTFIELD.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}