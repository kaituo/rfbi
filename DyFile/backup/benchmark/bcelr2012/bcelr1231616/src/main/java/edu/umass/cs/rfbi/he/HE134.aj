package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE134 {
	public int org.apache.bcel.generic.IFEQ.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IFEQ", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
