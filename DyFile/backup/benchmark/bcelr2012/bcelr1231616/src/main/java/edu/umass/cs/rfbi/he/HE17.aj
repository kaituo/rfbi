package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE17 {
	public int org.apache.bcel.generic.F2L.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.F2L", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}