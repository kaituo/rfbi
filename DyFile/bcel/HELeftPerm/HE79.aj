package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE79 {
	public int org.apache.bcel.generic.BASTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/BASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
