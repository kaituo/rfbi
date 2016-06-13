package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE49 {
	public int org.apache.bcel.generic.LAND.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/LAND", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
