package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE183 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* pass3aJList_valueChanged(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.pass3aJList_valueChanged", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}