package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE73 {
	public int org.apache.bcel.generic.DNEG.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.DNEG", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
