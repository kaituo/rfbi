package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE409 {
	before(org.apache.bcel.generic.FieldGen instance): execution(* removeObserver(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGen.removeObserver", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}