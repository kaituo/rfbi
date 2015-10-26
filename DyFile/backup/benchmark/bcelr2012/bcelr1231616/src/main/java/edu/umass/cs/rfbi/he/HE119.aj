package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE119 {
	public int org.apache.bcel.generic.TABLESWITCH.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.TABLESWITCH", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
