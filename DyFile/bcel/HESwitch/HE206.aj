package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE206 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* containsField(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.containsField", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}