package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE149 {
	public int org.apache.bcel.generic.ICONST.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/ICONST", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
