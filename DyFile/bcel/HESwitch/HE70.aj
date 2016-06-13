package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE70 {
	before(org.apache.bcel.classfile.ConstantFloat instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantFloat.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}