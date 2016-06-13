package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE136 {
	public int org.apache.bcel.generic.IF_ICMPLE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IF_ICMPLE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
