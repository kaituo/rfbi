package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE386 {
	before(org.apache.bcel.util.CodeHTML instance): execution(* codeToHTML(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.CodeHTML.codeToHTML", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}