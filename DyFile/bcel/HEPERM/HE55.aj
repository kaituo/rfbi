package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE55 {
	public int org.apache.bcel.generic.MONITORENTER.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/MONITORENTER", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
