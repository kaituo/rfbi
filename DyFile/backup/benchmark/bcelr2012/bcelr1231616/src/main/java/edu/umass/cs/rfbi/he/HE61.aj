package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE61 {
	public int org.apache.bcel.generic.RETURN.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.RETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
