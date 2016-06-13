package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE193 {
	before(org.apache.bcel.generic.InstructionHandle instance): execution(* removeTargeter(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionHandle.removeTargeter", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}