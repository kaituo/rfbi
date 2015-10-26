package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE58 {
	public int org.apache.bcel.generic.IDIV.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
