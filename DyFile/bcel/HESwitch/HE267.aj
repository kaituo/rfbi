package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE267 {
	before(org.apache.bcel.classfile.ConstantValue instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantValue.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}