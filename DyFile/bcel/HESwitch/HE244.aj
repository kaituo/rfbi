package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE244 {
	before(org.apache.bcel.generic.TABLESWITCH instance): execution(* dump(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.generic.TABLESWITCH.dump", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}