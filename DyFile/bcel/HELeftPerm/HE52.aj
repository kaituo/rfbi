package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE52 {
	public int org.apache.bcel.generic.IAND.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IAND", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
