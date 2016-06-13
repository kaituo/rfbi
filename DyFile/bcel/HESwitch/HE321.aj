package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE321 {
	before(org.apache.bcel.classfile.StackMapTable instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.StackMapTable.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}