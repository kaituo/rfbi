package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE391 {
	before(org.apache.bcel.generic.BIPUSH instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.BIPUSH.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}