package generated_tests;

import junit.framework.TestCase;
import edu.umass.cs.rfbi.tracer.*;
import edu.umass.cs.rfbi.tracer.item.*;
import edu.umass.cs.rfbi.generator.*;

public class Attribute extends TestCase {
	private Object thisObject;

	public void setUp() throws Exception {
		thisObject = TraceReader.readThisReceiver("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState/org.apache.bcel.classfile.Attribute.dump$925029252308234620.trace.gz");
		TraceReader.readListTraceItem("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/DscState/rfbi.org.apache.bcel.classfile.Attribute.testdump$7910801035121104671.trace.gz");

	}


	public void testdump_0() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.io.IOException {
		TraceReader.setMethodTraceItem(0);
		java.io.DataOutputStream arg_0 = null;

		String classTypeNames[] = {"java.io.DataOutputStream"};

		new ProxyObject(thisObject).call("dump", classTypeNames ,arg_0);
	}

	public void testdump_1() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.io.IOException {
		TraceReader.setMethodTraceItem(1);
		java.io.DataOutputStream arg_0 = (java.io.DataOutputStream)TraceReader.readObject(0);

		String classTypeNames[] = {"java.io.DataOutputStream"};

		new ProxyObject(thisObject).call("dump", classTypeNames ,arg_0);
	}
}

