package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE334 {
	before(org.apache.bcel.classfile.RuntimeInvisibleParameterAnnotations instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.RuntimeInvisibleParameterAnnotations.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}