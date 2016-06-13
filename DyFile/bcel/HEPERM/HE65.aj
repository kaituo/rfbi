package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE65 {
	public int org.apache.bcel.generic.FRETURN.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FRETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
