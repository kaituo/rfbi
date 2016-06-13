package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE234 {
	before(org.apache.bcel.verifier.statics.Pass2Verifier instance): execution(* getLocalVariablesInfo(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.Pass2Verifier.getLocalVariablesInfo", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}