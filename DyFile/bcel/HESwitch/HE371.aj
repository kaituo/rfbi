package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE371 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* delete(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.delete", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}