package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE403 {
	before(org.apache.bcel.generic.FieldGenOrMethodGen instance): execution(* removeAnnotationEntry(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.FieldGenOrMethodGen.removeAnnotationEntry", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}