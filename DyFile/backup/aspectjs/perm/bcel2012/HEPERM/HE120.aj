package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE120 {
	public int org.apache.bcel.generic.INSTANCEOF.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.INSTANCEOF", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
