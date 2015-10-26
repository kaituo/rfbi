package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE7 {
	public int org.apache.bcel.generic.FCMPL.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.FCMPL", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
