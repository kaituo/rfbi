package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE59 {
	before(org.apache.bcel.util.SyntheticRepository instance): execution(* findClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.SyntheticRepository.findClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}