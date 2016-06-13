package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE278 {
	before(org.apache.bcel.classfile.EnumElementValue instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.EnumElementValue.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}