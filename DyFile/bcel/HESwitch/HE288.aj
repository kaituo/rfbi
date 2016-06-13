package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE288 {
	before(org.apache.bcel.classfile.Synthetic instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Synthetic.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}