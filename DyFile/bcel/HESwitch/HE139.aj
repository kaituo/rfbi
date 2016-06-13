package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE139 {
	before(org.apache.bcel.util.MethodHTML instance): execution(* writeMethod(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.MethodHTML.writeMethod", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}