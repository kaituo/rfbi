package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE92 {
	before(org.apache.bcel.classfile.LineNumberTable instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.LineNumberTable.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}