package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE200 {
	before(org.apache.bcel.util.ClassSet instance): execution(* remove(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassSet.remove", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}