package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE145 {
	public int org.apache.bcel.generic.GOTO_W.hashCode() {
		RFile.writeDE2("org/apache/bcel/generic/GOTO_W", "/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEPERM/runtimeFile");
		return super.hashCode();
	}
}
