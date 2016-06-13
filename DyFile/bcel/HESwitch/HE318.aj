package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE318 {
	before(org.apache.bcel.classfile.RuntimeVisibleAnnotations instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.RuntimeVisibleAnnotations.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}