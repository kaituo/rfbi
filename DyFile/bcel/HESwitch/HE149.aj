package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE149 {
	before(org.apache.bcel.verifier.VerifierFactoryListModel instance): execution(* update(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.VerifierFactoryListModel.update", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}