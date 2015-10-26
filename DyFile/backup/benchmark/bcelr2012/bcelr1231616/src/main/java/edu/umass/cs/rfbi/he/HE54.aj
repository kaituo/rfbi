package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE54 {
	public int org.apache.bcel.generic.LDC_W.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LDC_W", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
