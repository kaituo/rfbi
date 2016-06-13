package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE415 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeException(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeException", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}