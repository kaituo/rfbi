package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE22 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* aboutMenuItem_actionPerformed(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.aboutMenuItem_actionPerformed", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}