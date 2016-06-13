package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE101 {
	before(org.apache.bcel.classfile.StackMap instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMap.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}