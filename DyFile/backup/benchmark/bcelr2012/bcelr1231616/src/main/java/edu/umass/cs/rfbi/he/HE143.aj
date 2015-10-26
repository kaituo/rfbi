package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE143 {
	public int org.apache.bcel.generic.IF_ACMPEQ.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IF_ACMPEQ", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
