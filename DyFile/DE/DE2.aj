package edu.umass.cs.rfbi.de;

import edu.umass.cs.rfbi.util.RFile;

public aspect DE2 {
	pointcut concernedExeExc()
	: !within(DE2) && (cflow(execution(* org.apache.jackrabbit.core.SessionImpl.getQPath(..))) && handler(java.lang.Exception+));

	before() : concernedExeExc() {
		RFile.writeDE2("At LuceneQueryBuilder.java:[line 1166]", "/home/kaituo/code/rfbi/findbugs/DyFile/DE/dde2.txt");
	}
}