package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE56 {
	before(org.apache.bcel.generic.GOTO_W instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.GOTO_W.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}