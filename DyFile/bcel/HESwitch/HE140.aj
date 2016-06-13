package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE140 {
	before(org.apache.bcel.generic.Instruction instance): execution(* equals(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.Instruction.equals", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}