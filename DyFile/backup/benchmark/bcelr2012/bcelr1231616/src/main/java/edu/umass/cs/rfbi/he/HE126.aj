package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE126 {
	public int org.apache.bcel.generic.INVOKEINTERFACE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.INVOKEINTERFACE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
