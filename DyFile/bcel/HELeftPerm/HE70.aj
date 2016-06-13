package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE70 {
	public int org.apache.bcel.generic.FSUB.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FSUB", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
