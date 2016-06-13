package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE42 {
	public int org.apache.bcel.generic.NOP.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/NOP", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
