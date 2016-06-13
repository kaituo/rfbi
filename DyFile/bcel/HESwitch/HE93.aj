package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE93 {
	before(org.apache.bcel.verifier.statics.LocalVariableInfo instance): execution(* setName(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.LocalVariableInfo.setName", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}