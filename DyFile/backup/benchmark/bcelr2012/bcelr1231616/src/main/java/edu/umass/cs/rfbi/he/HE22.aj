package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE22 {
	public int org.apache.bcel.generic.INEG.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.INEG", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
