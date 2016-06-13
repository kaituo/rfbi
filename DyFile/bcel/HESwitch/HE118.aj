package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE118 {
	before(org.apache.bcel.classfile.Signature instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Signature.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}