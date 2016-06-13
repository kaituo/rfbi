package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE302 {
	before(org.apache.bcel.generic.NEWARRAY instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.NEWARRAY.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}