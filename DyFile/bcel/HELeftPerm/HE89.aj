package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE89 {
	public int org.apache.bcel.generic.ILOAD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/ILOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
