package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE33 {
	public int org.apache.bcel.generic.LNEG.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LNEG", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
