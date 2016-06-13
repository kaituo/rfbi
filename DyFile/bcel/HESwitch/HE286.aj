package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE286 {
	before(org.apache.bcel.verifier.VerifyDialog instance): execution(* handleException(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifyDialog.handleException", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}