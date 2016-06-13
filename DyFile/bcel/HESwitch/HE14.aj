package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE14 {
	before(org.apache.bcel.classfile.FieldOrMethod instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.FieldOrMethod.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}