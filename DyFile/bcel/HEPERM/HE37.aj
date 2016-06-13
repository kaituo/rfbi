package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE37 {
	public int org.apache.bcel.generic.INVOKESPECIAL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/INVOKESPECIAL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
