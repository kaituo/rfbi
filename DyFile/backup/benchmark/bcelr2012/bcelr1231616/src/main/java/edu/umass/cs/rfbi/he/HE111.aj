package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE111 {
	public int org.apache.bcel.generic.DSUB.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.DSUB", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
