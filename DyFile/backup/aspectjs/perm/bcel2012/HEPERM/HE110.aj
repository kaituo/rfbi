package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE110 {
	public int org.apache.bcel.generic.POP2.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.POP2", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
