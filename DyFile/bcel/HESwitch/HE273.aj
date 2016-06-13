package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE273 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* insert(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.insert", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}