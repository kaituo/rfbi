package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE97 {
	before(org.apache.bcel.classfile.Code instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Code.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}