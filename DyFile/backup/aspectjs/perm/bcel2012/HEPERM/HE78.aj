package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE78 {
	public int org.apache.bcel.generic.IREM.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IREM", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}