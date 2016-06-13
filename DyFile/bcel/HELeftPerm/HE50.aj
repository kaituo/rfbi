package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE50 {
	public int org.apache.bcel.generic.FMUL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
