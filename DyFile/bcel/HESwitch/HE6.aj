package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE6 {
	before(org.apache.bcel.classfile.ParameterAnnotations instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ParameterAnnotations.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}