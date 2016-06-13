package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE4 {
	public int org.apache.bcel.generic.FDIV.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FDIV", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
