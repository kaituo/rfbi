package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE82 {
	public int org.apache.bcel.generic.I2F.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.I2F", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
