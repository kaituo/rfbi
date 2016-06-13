package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE101 {
	public int org.apache.bcel.generic.INVOKEVIRTUAL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/INVOKEVIRTUAL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
