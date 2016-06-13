package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE127 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* processWindowEvent(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.processWindowEvent", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}