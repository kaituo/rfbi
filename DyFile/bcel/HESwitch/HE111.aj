package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE111 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitMONITORENTER(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitMONITORENTER", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}