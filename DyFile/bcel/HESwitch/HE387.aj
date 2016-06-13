package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE387 {
	before(org.apache.bcel.generic.GOTO instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.GOTO.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}