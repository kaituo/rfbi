package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE390 {
	before(org.apache.bcel.classfile.ConstantNameAndType instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantNameAndType.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}