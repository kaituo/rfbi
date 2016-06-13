package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE110 {
	before(org.apache.bcel.util.Class2HTML instance): execution(* writeMainHTML(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.Class2HTML.writeMainHTML", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}