package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE98 {
	public int org.apache.bcel.generic.DALOAD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
