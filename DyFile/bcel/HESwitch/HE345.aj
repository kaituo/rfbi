package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE345 {
	before(org.apache.bcel.generic.BranchInstruction instance): execution(* getTargetOffset(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.BranchInstruction.getTargetOffset", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}