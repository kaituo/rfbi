package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE42 {
	before(org.apache.bcel.classfile.ConstantInterfaceMethodref instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantInterfaceMethodref.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}