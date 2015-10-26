package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE109 {
	public int org.apache.bcel.generic.GETFIELD.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.GETFIELD", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
