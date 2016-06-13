package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE31 {
	public int org.apache.bcel.generic.FREM.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/FREM", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
