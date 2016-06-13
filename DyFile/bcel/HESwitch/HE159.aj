package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE159 {
	before(org.apache.bcel.generic.RET instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.RET.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}