package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE389 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* replaceField(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.replaceField", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}