package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE162 {
	before(org.apache.bcel.generic.ClassGen instance): execution(* removeAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ClassGen.removeAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}