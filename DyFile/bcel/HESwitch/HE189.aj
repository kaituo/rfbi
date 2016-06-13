package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE189 {
	before(org.apache.bcel.generic.BranchHandle instance): execution(* setInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.BranchHandle.setInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}