package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE114 {
	before(org.apache.bcel.classfile.ConstantPool instance): execution(* getConstant(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantPool.getConstant", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}