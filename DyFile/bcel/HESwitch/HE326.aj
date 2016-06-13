package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE326 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addMethodref(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addMethodref", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}