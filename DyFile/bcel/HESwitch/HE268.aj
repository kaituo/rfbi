package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE268 {
	before(org.apache.bcel.util.ClassLoaderRepository instance): execution(* loadClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassLoaderRepository.loadClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}