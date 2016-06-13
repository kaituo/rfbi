package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE256 {
	before(org.apache.bcel.generic.LocalVariableInstruction instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LocalVariableInstruction.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}