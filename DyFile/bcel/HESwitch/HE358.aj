package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE358 {
	before(org.apache.bcel.classfile.ConstantClass instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantClass.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}