package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE1 {
	public int org.apache.bcel.generic.FASTORE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.FASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}