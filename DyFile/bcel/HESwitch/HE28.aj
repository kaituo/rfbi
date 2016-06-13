package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE28 {
	before(org.apache.bcel.classfile.Deprecated instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Deprecated.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}