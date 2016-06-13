package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE147 {
	public int org.apache.bcel.generic.IF_ICMPNE.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IF_ICMPNE", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
