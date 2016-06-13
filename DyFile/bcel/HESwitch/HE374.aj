package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE374 {
	before(org.apache.bcel.classfile.EnclosingMethod instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.EnclosingMethod.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}