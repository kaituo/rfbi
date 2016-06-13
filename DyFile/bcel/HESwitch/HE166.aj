package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE166 {
	before(org.apache.bcel.classfile.ElementValuePair instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ElementValuePair.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}