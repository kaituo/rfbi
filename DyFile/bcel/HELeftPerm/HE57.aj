package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE57 {
	public int org.apache.bcel.generic.IRETURN.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
