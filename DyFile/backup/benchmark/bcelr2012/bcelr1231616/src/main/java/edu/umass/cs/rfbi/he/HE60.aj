package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE60 {
	public int org.apache.bcel.generic.FALOAD.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.FALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}