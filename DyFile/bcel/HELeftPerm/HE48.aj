package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE48 {
	public int org.apache.bcel.generic.DUP_X1.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DUP_X1", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
