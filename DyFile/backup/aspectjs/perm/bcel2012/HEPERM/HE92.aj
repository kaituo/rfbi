package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE92 {
	public int org.apache.bcel.generic.IMUL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IMUL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
