package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE100 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitINSTANCEOF(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitINSTANCEOF", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}