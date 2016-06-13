package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE64 {
	before(org.apache.bcel.classfile.Annotations instance): execution(* writeAnnotations(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Annotations.writeAnnotations", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}