package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE27 {
	before(org.apache.bcel.generic.IINC instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.IINC.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}