package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE5 {
	public int org.apache.bcel.generic.LASTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LASTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
