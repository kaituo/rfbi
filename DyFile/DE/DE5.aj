package edu.umass.cs.rfbi.de;

import edu.umass.cs.rfbi.util.RFile;

public aspect DE5 {
	pointcut concernedExeExc()
	: !within(DE5) && (cflow(execution(* org.apache.jackrabbit.core.fs.FileSystem.close(..))) && handler(java.lang.Exception+));

	before() : concernedExeExc() {
		RFile.writeDE2("At BundleFsPersistenceManager.java:[line 477]", "/home/kaituo/code/rfbi/findbugs/DyFile/DE/dde2.txt");
	}
}