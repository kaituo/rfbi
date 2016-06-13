package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE414 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitBranchInstruction(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitBranchInstruction", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}