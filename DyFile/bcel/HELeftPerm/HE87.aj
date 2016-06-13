package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE87 {
	public int org.apache.bcel.generic.FSTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FSTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
