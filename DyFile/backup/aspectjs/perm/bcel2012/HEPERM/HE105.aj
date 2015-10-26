package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE105 {
	public int org.apache.bcel.generic.DMUL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.DMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
