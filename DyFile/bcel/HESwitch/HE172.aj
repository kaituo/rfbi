package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE172 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addInterfaceMethodref(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addInterfaceMethodref", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}