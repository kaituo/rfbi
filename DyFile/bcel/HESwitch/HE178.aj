package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE178 {
	before(org.apache.bcel.generic.InstructionFactory instance): execution(* createAppend(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionFactory.createAppend", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}