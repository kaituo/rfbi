package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE128 {
	public int org.apache.bcel.generic.IFLE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IFLE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}