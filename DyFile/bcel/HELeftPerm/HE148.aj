package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE148 {
	public int org.apache.bcel.generic.BIPUSH.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/BIPUSH", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
