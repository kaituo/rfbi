package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE74 {
	public int org.apache.bcel.generic.ISHR.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/ISHR", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
