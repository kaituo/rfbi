package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE131 {
	public int org.apache.bcel.generic.IFLT.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IFLT", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}