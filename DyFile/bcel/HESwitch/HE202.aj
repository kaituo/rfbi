package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE202 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* append(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.append", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}