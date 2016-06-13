package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE180 {
	before(org.apache.bcel.classfile.ClassElementValue instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ClassElementValue.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}