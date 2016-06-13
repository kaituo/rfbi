package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE36 {
	public int org.apache.bcel.generic.SWAP.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/SWAP", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
