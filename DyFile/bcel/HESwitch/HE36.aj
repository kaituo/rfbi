package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE36 {
	before(org.apache.bcel.verifier.statics.StringRepresentation instance): execution(* toString(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.statics.StringRepresentation.toString", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}