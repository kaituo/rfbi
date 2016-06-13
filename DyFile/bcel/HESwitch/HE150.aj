package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE150 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupUtf8(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupUtf8", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}