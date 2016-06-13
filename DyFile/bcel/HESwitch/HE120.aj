package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE120 {
	before(org.apache.bcel.generic.ConstantPoolGen instance): execution(* addClass_(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ConstantPoolGen.addClass_", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}