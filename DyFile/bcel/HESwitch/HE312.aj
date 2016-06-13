package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE312 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* move(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.move", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}