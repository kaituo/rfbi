package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE114 {
	public int org.apache.bcel.generic.GETSTATIC.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.GETSTATIC", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
