package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE98 {
	before(org.apache.bcel.classfile.StackMapType instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMapType.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}