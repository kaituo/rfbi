package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE74 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* removeMethod(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.removeMethod", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}