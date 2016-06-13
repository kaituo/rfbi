package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE411 {
	before(org.apache.bcel.verifier.structurals.ExceptionHandlers instance): execution(* getExceptionHandlers(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.ExceptionHandlers.getExceptionHandlers", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}