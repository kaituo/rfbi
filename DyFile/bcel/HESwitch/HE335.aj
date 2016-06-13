package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE335 {
	before(org.apache.bcel.util.MethodHTML instance): execution(* writeField(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.MethodHTML.writeField", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}