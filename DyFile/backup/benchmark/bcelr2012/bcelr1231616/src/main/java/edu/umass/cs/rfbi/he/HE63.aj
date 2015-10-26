package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE63 {
	public int org.apache.bcel.generic.ARRAYLENGTH.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.ARRAYLENGTH", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
