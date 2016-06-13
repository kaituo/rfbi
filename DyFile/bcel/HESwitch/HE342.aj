package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE342 {
	before(org.apache.bcel.classfile.LineNumberTable instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.LineNumberTable.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}