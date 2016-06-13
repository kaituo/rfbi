package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE290 {
	before(org.apache.bcel.verifier.VerifyDialog instance): execution(* pass1Button_ActionPerformed(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifyDialog.pass1Button_ActionPerformed", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}