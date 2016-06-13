package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE30 {
	before(org.apache.bcel.generic.INSTANCEOF instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.INSTANCEOF.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}