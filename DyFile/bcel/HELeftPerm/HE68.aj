package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE68 {
	public int org.apache.bcel.generic.IADD.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/IADD", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
