package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE296 {
	before(org.apache.bcel.generic.Select instance): execution(* updateTarget(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.Select.updateTarget", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}