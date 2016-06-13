package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE378 {
	before(org.apache.bcel.classfile.ConstantFieldref instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantFieldref.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}