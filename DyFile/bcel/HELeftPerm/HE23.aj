package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE23 {
	public int org.apache.bcel.generic.DCMPL.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/DCMPL", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
