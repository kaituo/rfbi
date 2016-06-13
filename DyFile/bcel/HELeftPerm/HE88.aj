package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE88 {
	public int org.apache.bcel.generic.LMUL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
