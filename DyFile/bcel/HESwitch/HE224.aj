package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE224 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addConstant(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addConstant", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}