package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE9 {
	before(org.apache.bcel.classfile.Unknown instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Unknown.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}