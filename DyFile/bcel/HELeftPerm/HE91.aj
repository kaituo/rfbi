package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE91 {
	public int org.apache.bcel.generic.CASTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/CASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
