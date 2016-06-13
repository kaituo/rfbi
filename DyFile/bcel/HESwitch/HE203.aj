package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE203 {
	before(org.apache.bcel.util.BCELFactory instance): execution(* createConstant(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.BCELFactory.createConstant", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}