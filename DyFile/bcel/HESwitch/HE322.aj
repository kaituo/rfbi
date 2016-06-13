package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE322 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}