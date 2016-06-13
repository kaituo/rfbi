package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE95 {
	before(org.apache.bcel.util.BCELifier instance): execution(* visitJavaClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELifier.visitJavaClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}