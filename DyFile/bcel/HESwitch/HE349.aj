package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE349 {
	before(org.apache.bcel.classfile.InnerClasses instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.InnerClasses.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}