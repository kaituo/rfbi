package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE45 {
	public int org.apache.bcel.generic.DASTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
