package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE263 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitAllocationInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitAllocationInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}