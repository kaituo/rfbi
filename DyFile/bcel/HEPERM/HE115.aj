package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE115 {
	public int org.apache.bcel.generic.ISTORE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/ISTORE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
