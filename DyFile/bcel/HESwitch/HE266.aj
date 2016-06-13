package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE266 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* visitCHECKCAST(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.visitCHECKCAST", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}