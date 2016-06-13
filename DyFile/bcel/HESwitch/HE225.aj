package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE225 {
	before(org.apache.bcel.classfile.Code instance): execution(* toString(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.classfile.Code.toString", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}