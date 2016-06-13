package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE51 {
	public int org.apache.bcel.generic.PUTSTATIC.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/PUTSTATIC", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
