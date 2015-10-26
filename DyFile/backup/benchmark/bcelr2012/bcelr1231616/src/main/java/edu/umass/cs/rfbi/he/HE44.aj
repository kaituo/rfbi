package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE44 {
	public int org.apache.bcel.generic.SASTORE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.SASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
