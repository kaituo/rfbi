package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE284 {
	before(org.apache.bcel.util.ClassPath instance): execution(* getResource(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassPath.getResource", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}