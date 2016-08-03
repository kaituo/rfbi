package generated_tests;

import org.junit.Test;
import org.junit.BeforeClass;
import edu.umass.cs.rfbi.tracer.*;
import edu.umass.cs.rfbi.tracer.item.*;
import edu.umass.cs.rfbi.generator.*;

public class JavaWrapper1790914218511826542 {
	@BeforeClass
	public static void setUp() throws Exception {
		TraceReader.readListTraceItem("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/DscState/org.apache.bcel.util.JavaWrapper.main$1790914218511826542.trace.gz");

	}


	@Test
	public void main_0() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.lang.Exception {
		System.out.println("main_0");
		TraceReader.setMethodTraceItem(0);
		Object arg_0 = null;

		String classTypeNames[] = {"[Ljava.lang.String;"};

		new ProxyObject(null, "org.apache.bcel.util.JavaWrapper").call("main", classTypeNames ,arg_0);
	}

	@Test
	public void main_1() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.lang.Exception {
		System.out.println("main_1");
		TraceReader.setMethodTraceItem(1);
		Object arg_0 = TraceReader.readObject(0);

		String classTypeNames[] = {"[Ljava.lang.String;"};

		new ProxyObject(null, "org.apache.bcel.util.JavaWrapper").call("main", classTypeNames ,arg_0);
	}

	@Test
	public void main_2() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.lang.Exception {
		System.out.println("main_2");
		TraceReader.setMethodTraceItem(2);
		Object arg_0 = TraceReader.readObject(0);

		String classTypeNames[] = {"[Ljava.lang.String;"};

		new ProxyObject(null, "org.apache.bcel.util.JavaWrapper").call("main", classTypeNames ,arg_0);
	}

	@Test
	public void main_3() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException, java.lang.Exception {
		System.out.println("main_3");
		TraceReader.setMethodTraceItem(3);
		Object arg_0 = TraceReader.readObject(0);

		String classTypeNames[] = {"[Ljava.lang.String;"};

		new ProxyObject(null, "org.apache.bcel.util.JavaWrapper").call("main", classTypeNames ,arg_0);
	}
}

