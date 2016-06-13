package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE279 {
	before(org.apache.bcel.classfile.LocalVariableTable instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.LocalVariableTable.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}