package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE181 {
	before(org.apache.bcel.classfile.LocalVariableTypeTable instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.LocalVariableTypeTable.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}