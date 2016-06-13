package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE351 {
	before(org.apache.bcel.generic.InstructionHandle instance): execution(* setInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionHandle.setInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}