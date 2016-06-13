package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE81 {
	before(org.apache.bcel.classfile.StackMapTable instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMapTable.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}