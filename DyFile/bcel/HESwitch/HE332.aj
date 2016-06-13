package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE332 {
	before(org.apache.bcel.classfile.ParameterAnnotationEntry instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ParameterAnnotationEntry.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}