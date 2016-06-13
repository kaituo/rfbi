package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE404 {
	before(org.apache.bcel.classfile.StackMap instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMap.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}