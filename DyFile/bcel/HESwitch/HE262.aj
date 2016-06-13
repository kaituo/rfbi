package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE262 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}