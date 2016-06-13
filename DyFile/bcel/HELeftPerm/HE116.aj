package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE116 {
	public int org.apache.bcel.generic.IASTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
