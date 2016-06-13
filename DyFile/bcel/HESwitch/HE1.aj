package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE1 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitRET(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitRET", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}