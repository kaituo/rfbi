package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE115 {
	before(org.apache.bcel.generic.ElementValuePairGen instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.ElementValuePairGen.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}