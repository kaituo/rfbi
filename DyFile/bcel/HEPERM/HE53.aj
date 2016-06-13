package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE53 {
	public int org.apache.bcel.generic.DREM.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
