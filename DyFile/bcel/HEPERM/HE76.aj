package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE76 {
	public int org.apache.bcel.generic.IOR.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IOR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
