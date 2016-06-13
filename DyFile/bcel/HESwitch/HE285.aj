package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE285 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* whatisMenuItem_actionPerformed(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.whatisMenuItem_actionPerformed", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}