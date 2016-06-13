package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE195 {
	before(org.apache.bcel.generic.InstructionHandle instance): execution(* addAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionHandle.addAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}