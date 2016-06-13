package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE333 {
	before(org.apache.bcel.generic.FieldGenOrMethodGen instance): execution(* setType(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGenOrMethodGen.setType", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}