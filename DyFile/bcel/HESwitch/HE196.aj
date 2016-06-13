package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE196 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* pass3bJList_valueChanged(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.pass3bJList_valueChanged", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}