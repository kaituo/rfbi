package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE365 {
	before(org.apache.bcel.util.BCELifier instance): execution(* visitMethod(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELifier.visitMethod", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}