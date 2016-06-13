package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE151 {
	public int org.apache.bcel.generic.SIPUSH.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/SIPUSH", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
