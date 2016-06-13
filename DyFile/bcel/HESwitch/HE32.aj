package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE32 {
	before(org.apache.bcel.util.InstructionFinder instance): execution(* search(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.InstructionFinder.search", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}