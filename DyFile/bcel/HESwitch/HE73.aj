package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE73 {
	before(org.apache.bcel.classfile.JavaClass instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.JavaClass.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}