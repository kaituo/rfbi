package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE108 {
	public int org.apache.bcel.generic.IMPDEP1.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.IMPDEP1", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
