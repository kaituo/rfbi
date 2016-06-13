package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE190 {
	before(org.apache.bcel.classfile.PMGClass instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.PMGClass.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}