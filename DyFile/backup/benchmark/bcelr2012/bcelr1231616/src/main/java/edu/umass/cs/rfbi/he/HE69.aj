package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE69 {
	public int org.apache.bcel.generic.NEW.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.NEW", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
