package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE112 {
	public int org.apache.bcel.generic.ASTORE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.ASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
