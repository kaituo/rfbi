package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE144 {
	public int org.apache.bcel.generic.IFNONNULL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IFNONNULL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
