package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE8 {
	public int org.apache.bcel.generic.FADD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FADD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
