package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE112 {
	before(org.apache.bcel.generic.Select instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.Select.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}