package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE131 {
	before(org.apache.bcel.util.ClassLoaderRepository instance): execution(* removeClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassLoaderRepository.removeClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}