package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE2 {
	public int org.apache.bcel.generic.ACONST_NULL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/ACONST_NULL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
