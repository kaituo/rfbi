package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE300 {
	before(org.apache.bcel.classfile.ConstantString instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantString.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}