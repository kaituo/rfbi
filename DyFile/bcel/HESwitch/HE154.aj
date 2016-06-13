package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE154 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addFieldref(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addFieldref", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}