package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE66 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* removeInterface(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.removeInterface", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}