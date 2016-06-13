package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE108 {
	before(org.apache.bcel.generic.PUTSTATIC instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.PUTSTATIC.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}