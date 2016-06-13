package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE317 {
	before(org.apache.bcel.classfile.SourceFile instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.SourceFile.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}