package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE40 {
	public int org.apache.bcel.generic.LSUB.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LSUB", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
