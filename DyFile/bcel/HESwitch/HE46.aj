package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE46 {
	before(org.apache.bcel.generic.AnnotationElementValueGen instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.AnnotationElementValueGen.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}