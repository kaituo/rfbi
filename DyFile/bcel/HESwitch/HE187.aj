package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE187 {
	before(org.apache.bcel.generic.INVOKEINTERFACE instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.INVOKEINTERFACE.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}