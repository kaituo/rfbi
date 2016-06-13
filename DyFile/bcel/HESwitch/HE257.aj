package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE257 {
	before(org.apache.bcel.generic.InstructionHandle instance): execution(* removeAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionHandle.removeAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}