package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE364 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* newFileMenuItem_actionPerformed(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.newFileMenuItem_actionPerformed", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}