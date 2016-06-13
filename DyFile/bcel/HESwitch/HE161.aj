package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE161 {
	before(org.apache.bcel.generic.BranchInstruction instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.BranchInstruction.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}