package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE133 {
	before(org.apache.bcel.classfile.PMGClass instance): execution(* accept(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.PMGClass.accept", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}