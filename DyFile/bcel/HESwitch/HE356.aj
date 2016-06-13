package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE356 {
	before(org.apache.bcel.verifier.statics.LocalVariableInfo instance): execution(* getType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.LocalVariableInfo.getType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}