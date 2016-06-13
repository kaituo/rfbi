package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE164 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeExceptionHandler(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeExceptionHandler", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}