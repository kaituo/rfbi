package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE220 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupInterfaceMethodref(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupInterfaceMethodref", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}