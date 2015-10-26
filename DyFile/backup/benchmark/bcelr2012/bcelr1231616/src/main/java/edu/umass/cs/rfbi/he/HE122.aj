package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE122 {
	public int org.apache.bcel.generic.IINC.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IINC", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
