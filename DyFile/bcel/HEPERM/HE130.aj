package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE130 {
	public int org.apache.bcel.generic.NEWARRAY.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/NEWARRAY", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
