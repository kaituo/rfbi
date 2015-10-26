package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE86 {
	public int org.apache.bcel.generic.I2D.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.I2D", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
