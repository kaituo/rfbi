package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE35 {
	before(org.apache.bcel.util.ClassPath instance): execution(* getClassFileInternal(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.ClassPath.getClassFileInternal", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}