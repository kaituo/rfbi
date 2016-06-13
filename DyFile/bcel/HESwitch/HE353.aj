package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE353 {
	before(org.apache.bcel.generic.ArrayElementValueGen instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ArrayElementValueGen.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}