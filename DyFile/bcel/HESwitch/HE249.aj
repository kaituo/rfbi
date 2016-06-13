package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE249 {
	before(org.apache.bcel.util.ConstantHTML instance): execution(* writeConstant(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ConstantHTML.writeConstant", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}