package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE80 {
	before(org.apache.bcel.util.AttributeHTML instance): execution(* writeAttribute(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.AttributeHTML.writeAttribute", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}