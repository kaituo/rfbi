package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE270 {
	before(org.apache.bcel.classfile.JavaClass instance): execution(* implementationOf(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.JavaClass.implementationOf", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}