package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE132 {
	before(org.apache.bcel.verifier.VerifierFactoryListModel instance): execution(* removeListDataListener(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierFactoryListModel.removeListDataListener", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}