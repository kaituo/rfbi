package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE155 {
	before(org.apache.bcel.classfile.Deprecated instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Deprecated.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}