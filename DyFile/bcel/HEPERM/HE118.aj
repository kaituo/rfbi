package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE118 {
	public int org.apache.bcel.generic.CHECKCAST.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/CHECKCAST", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
