package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE301 {
	before(org.apache.bcel.classfile.SimpleElementValue instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.SimpleElementValue.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}