package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE16 {
	public int org.apache.bcel.generic.LCMP.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LCMP", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
