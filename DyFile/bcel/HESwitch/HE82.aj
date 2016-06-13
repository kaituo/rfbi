package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE82 {
	before(org.apache.bcel.generic.LOOKUPSWITCH instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LOOKUPSWITCH.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}