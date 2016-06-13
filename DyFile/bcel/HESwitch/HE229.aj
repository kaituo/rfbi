package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE229 {
	before(org.apache.bcel.generic.JsrInstruction instance): execution(* getType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.JsrInstruction.getType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}