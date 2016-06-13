package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE254 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitLocalVariableInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitLocalVariableInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}