package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE331 {
	before(org.apache.bcel.generic.SimpleElementValueGen instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.SimpleElementValueGen.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}