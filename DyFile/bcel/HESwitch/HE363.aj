package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE363 {
	before(org.apache.bcel.classfile.Deprecated instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Deprecated.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}