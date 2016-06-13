package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE384 {
	before(org.apache.bcel.classfile.Method instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Method.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}