package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE287 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitINVOKEVIRTUAL(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitINVOKEVIRTUAL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}