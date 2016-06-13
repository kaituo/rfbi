package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE34 {
	public int org.apache.bcel.generic.LSHR.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LSHR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
