package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE313 {
	before(org.apache.bcel.generic.SIPUSH instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.SIPUSH.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}