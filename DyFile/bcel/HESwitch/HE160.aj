package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE160 {
	before(org.apache.bcel.classfile.CodeException instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.CodeException.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}