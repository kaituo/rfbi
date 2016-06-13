package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE201 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitInvokeInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitInvokeInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}