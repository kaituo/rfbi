package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE336 {
	before(org.apache.bcel.util.SyntheticRepository instance): execution(* storeClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.SyntheticRepository.storeClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}