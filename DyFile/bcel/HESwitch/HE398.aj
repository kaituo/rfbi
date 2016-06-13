package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE398 {
	before(org.apache.bcel.util.ClassPath instance): execution(* getInputStream(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassPath.getInputStream", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}