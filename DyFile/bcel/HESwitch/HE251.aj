package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE251 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeLocalVariable(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeLocalVariable", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}