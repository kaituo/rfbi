package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE71 {
	public int org.apache.bcel.generic.LLOAD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LLOAD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
