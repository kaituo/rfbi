package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE362 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeObserver(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeObserver", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}