package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE123 {
	public int org.apache.bcel.generic.LOOKUPSWITCH.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.LOOKUPSWITCH", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}