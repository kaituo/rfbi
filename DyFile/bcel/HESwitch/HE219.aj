package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE219 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addNameAndType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addNameAndType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}