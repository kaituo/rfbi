package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE56 {
	public int org.apache.bcel.generic.ISUB.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.ISUB", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
