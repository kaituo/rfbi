package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE16 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitFieldInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitFieldInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}