package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE395 {
	before(org.apache.bcel.generic.MethodGen instance): execution(* removeCodeAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.MethodGen.removeCodeAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}