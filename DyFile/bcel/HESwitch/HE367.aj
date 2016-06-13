package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE367 {
	before(org.apache.bcel.classfile.Signature instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Signature.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}