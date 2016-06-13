package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE48 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* replaceMethod(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.replaceMethod", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}