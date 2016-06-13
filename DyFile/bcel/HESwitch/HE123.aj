package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE123 {
	before(org.apache.bcel.classfile.ExceptionTable instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ExceptionTable.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}