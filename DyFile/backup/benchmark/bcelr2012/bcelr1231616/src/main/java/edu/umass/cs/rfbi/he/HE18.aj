package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE18 {
	public int org.apache.bcel.generic.D2F.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.D2F", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
