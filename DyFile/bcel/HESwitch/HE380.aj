package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE380 {
	before(org.apache.bcel.util.ClassSet instance): execution(* add(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassSet.add", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}