package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE150 {
	public int org.apache.bcel.generic.FCONST.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.FCONST", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
