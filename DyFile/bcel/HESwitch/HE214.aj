package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE214 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitLOOKUPSWITCH(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitLOOKUPSWITCH", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}