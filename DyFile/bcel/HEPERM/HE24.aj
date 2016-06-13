package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE24 {
	public int org.apache.bcel.generic.LXOR.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LXOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
