package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE93 {
	public int org.apache.bcel.generic.AASTORE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.AASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
