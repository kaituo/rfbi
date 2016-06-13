package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE21 {
	public int org.apache.bcel.generic.FCMPG.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FCMPG", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
