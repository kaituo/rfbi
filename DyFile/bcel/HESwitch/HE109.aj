package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE109 {
	before(org.apache.bcel.verifier.structurals.OperandStack instance): execution(* push(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.OperandStack.push", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}