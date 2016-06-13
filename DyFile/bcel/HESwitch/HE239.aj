package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE239 {
	before(org.apache.bcel.util.ClassLoader instance): execution(* createClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassLoader.createClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}