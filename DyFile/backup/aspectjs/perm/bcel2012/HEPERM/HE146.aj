package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE146 {
	public int org.apache.bcel.generic.GOTO.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.GOTO", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
