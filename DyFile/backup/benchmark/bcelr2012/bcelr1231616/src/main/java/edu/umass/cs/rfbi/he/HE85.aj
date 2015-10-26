package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE85 {
	public int org.apache.bcel.generic.I2B.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.I2B", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
