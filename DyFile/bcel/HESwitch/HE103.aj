package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE103 {
	before(org.apache.bcel.generic.LDC instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LDC.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}