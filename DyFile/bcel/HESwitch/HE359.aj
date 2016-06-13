package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE359 {
	before(org.apache.bcel.classfile.ArrayElementValue instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.ArrayElementValue.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}