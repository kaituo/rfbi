package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE106 {
	public int org.apache.bcel.generic.SALOAD.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.SALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
