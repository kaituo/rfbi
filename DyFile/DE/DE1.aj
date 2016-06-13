package edu.umass.cs.rfbi.de;

import edu.umass.cs.rfbi.util.RFile;

public aspect DE1 {
	pointcut concernedExeExc()
	: !within(DE1) && (cflow(execution(* org.apache.jackrabbit.core.fs.FileSystem.close(..))) && handler(java.lang.Exception+));

	before() : concernedExeExc() {
		RFile.writeDE2("At BundleDbPersistenceManager.java:[line 1191]", "/home/kaituo/code/rfbi/findbugs/DyFile/DE/dde2.txt");
	}
}