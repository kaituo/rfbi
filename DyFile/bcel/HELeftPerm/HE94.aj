package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE94 {
	public int org.apache.bcel.generic.PUTFIELD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/PUTFIELD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
