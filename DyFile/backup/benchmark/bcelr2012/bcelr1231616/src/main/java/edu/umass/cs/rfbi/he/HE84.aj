package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE84 {
	public int org.apache.bcel.generic.POP.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.POP", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
