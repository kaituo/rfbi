package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE12 {
	before(org.apache.bcel.classfile.ConstantCP instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ConstantCP.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}