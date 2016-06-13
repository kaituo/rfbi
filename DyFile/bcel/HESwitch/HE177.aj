package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE177 {
	before(org.apache.bcel.verifier.statics.LocalVariableInfo instance): execution(* setType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.LocalVariableInfo.setType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}