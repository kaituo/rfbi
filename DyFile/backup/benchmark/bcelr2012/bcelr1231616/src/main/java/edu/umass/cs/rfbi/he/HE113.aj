package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE113 {
	public int org.apache.bcel.generic.IUSHR.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IUSHR", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
