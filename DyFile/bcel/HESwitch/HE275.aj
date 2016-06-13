package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE275 {
	before(org.apache.bcel.verifier.structurals.InstConstraintVisitor instance): execution(* visitINVOKESTATIC(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.verifier.structurals.InstConstraintVisitor.visitINVOKESTATIC", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}