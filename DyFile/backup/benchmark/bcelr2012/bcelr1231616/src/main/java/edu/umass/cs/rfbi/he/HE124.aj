package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE124 {
	public int org.apache.bcel.generic.MULTIANEWARRAY.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.MULTIANEWARRAY", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}