package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE105 {
	before(org.apache.bcel.verifier.statics.LocalVariableInfo instance): execution(* add(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.LocalVariableInfo.add", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}