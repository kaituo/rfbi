package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE25 {
	public int org.apache.bcel.generic.LREM.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
