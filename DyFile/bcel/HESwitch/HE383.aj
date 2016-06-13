package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE383 {
	before(org.apache.bcel.generic.JSR_W instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.JSR_W.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}