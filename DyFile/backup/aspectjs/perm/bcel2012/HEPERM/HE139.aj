package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE139 {
	public int org.apache.bcel.generic.IF_ICMPEQ.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IF_ICMPEQ", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
