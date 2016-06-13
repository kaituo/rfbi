package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE152 {
	public int org.apache.bcel.generic.DCONST.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DCONST", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
