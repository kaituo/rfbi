package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE410 {
	before(org.apache.bcel.classfile.Unknown instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Unknown.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}