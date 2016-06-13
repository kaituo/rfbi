package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE227 {
	before(org.apache.bcel.generic.FieldGenOrMethodGen instance): execution(* removeAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGenOrMethodGen.removeAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}