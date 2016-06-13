package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE132 {
	public int org.apache.bcel.generic.IFGT.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IFGT", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
