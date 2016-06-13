package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE163 {
	before(org.apache.bcel.classfile.Field instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Field.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}