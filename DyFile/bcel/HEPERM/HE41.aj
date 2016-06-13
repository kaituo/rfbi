package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE41 {
	public int org.apache.bcel.generic.DSTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DSTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
