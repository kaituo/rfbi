package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE216 {
	before(org.apache.bcel.verifier.structurals.OperandStack instance): execution(* merge(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.OperandStack.merge", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}