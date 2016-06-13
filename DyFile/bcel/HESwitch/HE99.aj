package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE99 {
	before(org.apache.bcel.util.SyntheticRepository instance): execution(* loadClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.SyntheticRepository.loadClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}