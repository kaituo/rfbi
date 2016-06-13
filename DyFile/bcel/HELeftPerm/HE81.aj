package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE81 {
	public int org.apache.bcel.generic.I2L.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/I2L", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
