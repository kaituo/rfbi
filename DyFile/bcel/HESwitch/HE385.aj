package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE385 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupNameAndType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupNameAndType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}