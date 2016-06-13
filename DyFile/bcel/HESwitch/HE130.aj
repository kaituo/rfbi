package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE130 {
	before(org.apache.bcel.generic.INVOKEINTERFACE instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.INVOKEINTERFACE.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}