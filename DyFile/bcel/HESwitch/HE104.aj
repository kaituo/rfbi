package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE104 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* lookupFieldref(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.lookupFieldref", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}