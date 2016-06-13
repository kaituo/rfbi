package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE34 {
	before(org.apache.bcel.classfile.SourceFile instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.SourceFile.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}