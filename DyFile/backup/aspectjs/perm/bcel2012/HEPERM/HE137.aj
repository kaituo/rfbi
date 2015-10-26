package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE137 {
	public int org.apache.bcel.generic.IF_ACMPNE.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IF_ACMPNE", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
