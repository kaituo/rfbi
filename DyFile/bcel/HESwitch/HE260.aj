package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE260 {
	before(org.apache.bcel.classfile.RuntimeInvisibleAnnotations instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.RuntimeInvisibleAnnotations.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}