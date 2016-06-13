package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE352 {
	before(org.apache.bcel.classfile.ConstantLong instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantLong.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}