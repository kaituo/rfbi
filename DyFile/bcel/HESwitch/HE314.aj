package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE314 {
	before(org.apache.bcel.generic.InstructionFactory instance): execution(* createConstant(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionFactory.createConstant", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}