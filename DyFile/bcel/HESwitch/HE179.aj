package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE179 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupString(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupString", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}