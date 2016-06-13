package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE413 {
	before(org.apache.bcel.generic.CodeExceptionGen instance): execution(* updateTarget(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.CodeExceptionGen.updateTarget", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}