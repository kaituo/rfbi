package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE53 {
	before(org.apache.bcel.generic.BranchInstruction instance): execution(* updateTarget(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.BranchInstruction.updateTarget", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}