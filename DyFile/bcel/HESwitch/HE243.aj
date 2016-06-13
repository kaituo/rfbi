package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE243 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* addEmptyConstructor(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.addEmptyConstructor", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}