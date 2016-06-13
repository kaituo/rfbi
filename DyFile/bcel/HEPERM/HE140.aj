package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE140 {
	public int org.apache.bcel.generic.LDC.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LDC", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
