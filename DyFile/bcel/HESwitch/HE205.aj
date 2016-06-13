package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE205 {
	before(org.apache.bcel.classfile.ExceptionTable instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ExceptionTable.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}