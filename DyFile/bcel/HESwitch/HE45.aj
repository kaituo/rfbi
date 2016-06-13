package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE45 {
	before(org.apache.bcel.classfile.FieldOrMethod instance): execution(* copy_(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.FieldOrMethod.copy_", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}