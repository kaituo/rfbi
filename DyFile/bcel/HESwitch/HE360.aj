package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE360 {
	before(org.apache.bcel.classfile.StackMapTableEntry instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMapTableEntry.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}