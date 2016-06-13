package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE13 {
	before(org.apache.bcel.verifier.structurals.ExecutionVisitor instance): execution(* visitJSR_W(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.ExecutionVisitor.visitJSR_W", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}