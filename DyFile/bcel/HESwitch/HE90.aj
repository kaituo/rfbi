package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE90 {
	before(org.apache.bcel.util.ClassLoaderRepository instance): execution(* storeClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassLoaderRepository.storeClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}