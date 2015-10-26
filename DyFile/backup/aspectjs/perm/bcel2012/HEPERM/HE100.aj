package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE100 {
	public int org.apache.bcel.generic.ARETURN.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.ARETURN", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
