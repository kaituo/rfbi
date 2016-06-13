package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE15 {
	public int org.apache.bcel.generic.MONITOREXIT.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/MONITOREXIT", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
