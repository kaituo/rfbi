package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE152 {
	before(org.apache.bcel.generic.RET instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.RET.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}