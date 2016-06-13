package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE3 {
	public int org.apache.bcel.generic.LOR.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
