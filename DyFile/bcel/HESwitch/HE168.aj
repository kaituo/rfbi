package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE168 {
	before(org.apache.bcel.generic.InstructionList instance): execution(* removeObserver(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.InstructionList.removeObserver", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}