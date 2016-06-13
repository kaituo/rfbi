package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE157 {
	before(org.apache.bcel.generic.LineNumberGen instance): execution(* updateTarget(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.LineNumberGen.updateTarget", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}