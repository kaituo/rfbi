package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE129 {
	public int org.apache.bcel.generic.IFNULL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IFNULL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}