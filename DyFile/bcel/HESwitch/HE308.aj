package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE308 {
	before(org.apache.bcel.verifier.structurals.ExecutionVisitor instance): execution(* visitJSR(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.ExecutionVisitor.visitJSR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}