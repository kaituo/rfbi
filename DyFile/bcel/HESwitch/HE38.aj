package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE38 {
	before(org.apache.bcel.classfile.SourceFile instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.SourceFile.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}