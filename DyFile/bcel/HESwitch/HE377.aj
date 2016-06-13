package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE377 {
	before(org.apache.bcel.verifier.statics.LocalVariableInfo instance): execution(* getName(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.LocalVariableInfo.getName", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}