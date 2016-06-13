package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE337 {
	before(org.apache.bcel.classfile.ConstantUtf8 instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantUtf8.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}