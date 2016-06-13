package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE55 {
	before(org.apache.bcel.classfile.Attribute instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Attribute.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}