package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.TraceWriter;

privileged aspect HE158 {
	before(org.apache.bcel.util.SyntheticRepository instance): execution(* removeClass(..)) && this(instance) {
		TraceWriter.writeState(instance, "org.apache.bcel.util.SyntheticRepository.removeClass", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState");
	}
}