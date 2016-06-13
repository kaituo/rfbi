package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE310 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}