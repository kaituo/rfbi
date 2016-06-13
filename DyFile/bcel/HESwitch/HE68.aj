package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE68 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitArrayInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitArrayInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}