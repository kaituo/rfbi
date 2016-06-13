package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE76 {
	before(org.apache.bcel.classfile.Unknown instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Unknown.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}