package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE67 {
	public int org.apache.bcel.generic.DADD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DADD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
