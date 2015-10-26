package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE107 {
	public int org.apache.bcel.generic.IMPDEP2.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IMPDEP2", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
