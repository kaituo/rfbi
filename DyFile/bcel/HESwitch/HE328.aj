package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE328 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitReturnInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitReturnInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}