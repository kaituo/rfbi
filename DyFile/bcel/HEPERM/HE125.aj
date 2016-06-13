package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE125 {
	public int org.apache.bcel.generic.IFNE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IFNE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
