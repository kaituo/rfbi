package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE142 {
	public int org.apache.bcel.generic.IF_ICMPGT.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IF_ICMPGT", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}