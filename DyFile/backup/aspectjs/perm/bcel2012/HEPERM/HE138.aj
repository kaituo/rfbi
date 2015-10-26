package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE138 {
	public int org.apache.bcel.generic.IF_ICMPLT.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IF_ICMPLT", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
