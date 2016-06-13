package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE119 {
	before(org.apache.bcel.generic.ReturnaddressType instance): execution(* equals(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ReturnaddressType.equals", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}