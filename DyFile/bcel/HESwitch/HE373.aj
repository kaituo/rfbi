package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE373 {
	before(org.apache.bcel.classfile.ConstantInteger instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantInteger.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}