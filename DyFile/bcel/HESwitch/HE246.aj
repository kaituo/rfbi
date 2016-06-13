package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE246 {
	before(org.apache.bcel.classfile.AnnotationEntry instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.AnnotationEntry.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}