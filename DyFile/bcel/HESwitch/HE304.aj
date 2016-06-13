package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE304 {
	before(org.apache.bcel.util.ClassLoader instance): execution(* loadClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassLoader.loadClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}