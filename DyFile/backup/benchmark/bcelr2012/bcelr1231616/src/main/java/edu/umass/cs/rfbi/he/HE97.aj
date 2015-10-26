package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE97 {
	public int org.apache.bcel.generic.DDIV.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.DDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
