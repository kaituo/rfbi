package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE62 {
	public int org.apache.bcel.generic.BALOAD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/BALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
