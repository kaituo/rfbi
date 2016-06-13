package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE405 {
	before(org.apache.bcel.classfile.ConstantMethodref instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantMethodref.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}