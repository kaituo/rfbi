package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE89 {
	before(org.apache.bcel.util.JavaWrapper instance): execution(* runMain(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.JavaWrapper.runMain", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}