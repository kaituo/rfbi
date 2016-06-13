package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE153 {
	before(org.apache.bcel.generic.FieldGen instance): execution(* checkType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGen.checkType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}