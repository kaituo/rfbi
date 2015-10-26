package edu.umass.cs.rfbi.he;

import edu.umass.cs.rfbi.util.RFile;

public aspect HE95 {
	public int org.apache.bcel.generic.Instruction.hashCode() {
		RFile.writeDE2("org.apache.bcel.generic.Instruction", "/home/kaituo/code/rfbi/findbugs/DyFile/HEPERM/runtime.txt");
		return super.hashCode();
	}
}
