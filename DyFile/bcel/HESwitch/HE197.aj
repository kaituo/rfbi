package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE197 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addString(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addString", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}