package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE221 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* setPositions(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.setPositions", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}