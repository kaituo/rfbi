package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE126 {
	before(org.apache.bcel.generic.FieldGen instance): execution(* copy(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGen.copy", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}