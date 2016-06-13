package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE33 {
	before(org.apache.bcel.util.ClassPath instance): execution(* getResources(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassPath.getResources", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}