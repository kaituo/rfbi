package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE52 {
	before(org.apache.bcel.util.ClassPath instance): execution(* getResourceAsStream(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassPath.getResourceAsStream", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}