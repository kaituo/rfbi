package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE20 {
	public int org.apache.bcel.generic.DUP2.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DUP2", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
