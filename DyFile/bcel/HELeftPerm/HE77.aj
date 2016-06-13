package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE77 {
	public int org.apache.bcel.generic.IALOAD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IALOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
