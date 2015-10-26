package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE99 {
	public int org.apache.bcel.generic.FLOAD.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.FLOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
