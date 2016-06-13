package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE407 {
	before(org.apache.bcel.classfile.LocalVariableTypeTable instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.LocalVariableTypeTable.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}