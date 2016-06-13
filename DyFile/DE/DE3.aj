package edu.umass.cs.rfbi.de;

import edu.umass.cs.rfbi.util.RFile;

public aspect DE3 {
	pointcut concernedExeExc()
	: !within(DE3) && (cflow(execution(* org.apache.jackrabbit.core.SessionImpl.getQName(..))) && handler(java.lang.Exception+));

	before() : concernedExeExc() {
		RFile.writeDE2("At LuceneQueryBuilder.java:[line 1176]", "/home/kaituo/code/rfbi/findbugs/DyFile/DE/dde2.txt");
	}
}