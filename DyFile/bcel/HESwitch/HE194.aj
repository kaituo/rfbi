package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE194 {
	before(org.apache.bcel.generic.MULTIANEWARRAY instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MULTIANEWARRAY.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}