package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE57 {
	before(org.apache.bcel.classfile.ConstantString instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantString.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}