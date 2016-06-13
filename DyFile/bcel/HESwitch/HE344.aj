package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE344 {
	before(org.apache.bcel.util.BCELifier instance): execution(* visitField(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELifier.visitField", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}