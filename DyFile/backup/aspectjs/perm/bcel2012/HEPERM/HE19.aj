package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE19 {
	public int org.apache.bcel.generic.F2I.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.F2I", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
