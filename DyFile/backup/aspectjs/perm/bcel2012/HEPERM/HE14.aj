package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE14 {
	public int org.apache.bcel.generic.DUP2_X1.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.DUP2_X1", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
