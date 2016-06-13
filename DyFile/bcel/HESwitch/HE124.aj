package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE124 {
	before(org.apache.bcel.classfile.Synthetic instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Synthetic.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}