package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE198 {
	before(org.apache.bcel.generic.InstructionHandle instance): execution(* getAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionHandle.getAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}