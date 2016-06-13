package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE217 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* addLocalVariable(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.addLocalVariable", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}