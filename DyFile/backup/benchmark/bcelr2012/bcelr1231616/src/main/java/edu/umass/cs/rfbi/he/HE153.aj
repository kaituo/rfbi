package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE153 {
	public int org.apache.bcel.generic.LCONST.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LCONST", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
