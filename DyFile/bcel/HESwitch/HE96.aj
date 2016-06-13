package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE96 {
	before(org.apache.bcel.util.CodeHTML instance): execution(* writeMethod(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.CodeHTML.writeMethod", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}