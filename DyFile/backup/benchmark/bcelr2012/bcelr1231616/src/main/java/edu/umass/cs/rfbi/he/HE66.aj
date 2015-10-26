package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE66 {
	public int org.apache.bcel.generic.INVOKESTATIC.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.INVOKESTATIC", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
