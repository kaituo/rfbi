package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE6 {
	public int org.apache.bcel.generic.LADD.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LADD", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
