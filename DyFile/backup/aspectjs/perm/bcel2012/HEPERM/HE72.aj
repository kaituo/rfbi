package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE72 {
	public int org.apache.bcel.generic.ISHL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.ISHL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
