package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE259 {
	before(org.apache.bcel.classfile.AnnotationElementValue instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.AnnotationElementValue.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}