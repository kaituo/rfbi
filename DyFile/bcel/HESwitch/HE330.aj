package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE330 {
	before(org.apache.bcel.classfile.Code instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Code.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}