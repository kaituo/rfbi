package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE218 {
	before(org.apache.bcel.classfile.InnerClasses instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.InnerClasses.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}