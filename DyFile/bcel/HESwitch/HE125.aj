package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE125 {
	before(org.apache.bcel.generic.LocalVariableGen instance): execution(* updateTarget(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LocalVariableGen.updateTarget", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}