package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE87 {
	before(org.apache.bcel.generic.AnnotationEntryGen instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.AnnotationEntryGen.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}