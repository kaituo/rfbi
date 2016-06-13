package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE231 {
	before(org.apache.bcel.verifier.VerifierAppFrame instance): execution(* classNamesJList_valueChanged(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierAppFrame.classNamesJList_valueChanged", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}