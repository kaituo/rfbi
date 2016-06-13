package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE49 {
	before(org.apache.bcel.generic.InstructionFactory instance): execution(* createCast(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionFactory.createCast", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}