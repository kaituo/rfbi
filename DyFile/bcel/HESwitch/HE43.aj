package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE43 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeLineNumber(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeLineNumber", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}