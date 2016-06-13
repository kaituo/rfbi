package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE46 {
	public int org.apache.bcel.generic.DUP.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DUP", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
