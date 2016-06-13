package edu.umass.cs.rfbi.de;

import edu.umass.cs.rfbi.util.RFile;

public aspect DE4 {
	pointcut concernedExeExc()
	: !within(DE4) && (cflow(execution(* org.apache.jackrabbit.core.query.lucene.NamespaceMappings.translateName(..))) && handler(java.lang.Exception+));

	before() : concernedExeExc() {
		RFile.writeDE2("At LuceneQueryBuilder.java:[line 1176]", "/home/kaituo/code/rfbi/findbugs/DyFile/DE/dde2.txt");
	}
}