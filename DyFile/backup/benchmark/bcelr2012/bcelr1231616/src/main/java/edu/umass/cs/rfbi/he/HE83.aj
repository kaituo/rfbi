package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE83 {
	public int org.apache.bcel.generic.I2C.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.I2C", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
