package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE28 {
	public int org.apache.bcel.generic.L2F.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/L2F", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
