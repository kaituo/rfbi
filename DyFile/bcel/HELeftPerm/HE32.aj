package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE32 {
	public int org.apache.bcel.generic.LSHL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LSHL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
