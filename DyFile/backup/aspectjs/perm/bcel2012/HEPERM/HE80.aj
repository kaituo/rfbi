package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE80 {
	public int org.apache.bcel.generic.I2S.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.I2S", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
