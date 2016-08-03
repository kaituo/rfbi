package generated_tests;

import org.junit.Test;
import org.junit.BeforeClass;
import edu.umass.cs.rfbi.tracer.*;
import edu.umass.cs.rfbi.tracer.item.*;
import edu.umass.cs.rfbi.generator.*;

public class InstructionList1640346338716515174 {
	private static Object thisObject;

	@BeforeClass
	public static void setUp() throws Exception {
		thisObject = TraceReader.readThisReceiver("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState/org.apache.bcel.generic.InstructionList.setPositions$978916964499685650.trace.gz");
		TraceReader.readListTraceItem("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/DscState/rfbi.org.apache.bcel.generic.InstructionList.testsetPositions$1640346338716515174.trace.gz");

	}


	@Test
	public void testsetPositions_0() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(0);
		boolean arg_0 = false;

		String classTypeNames[] = {"boolean"};

		new ProxyObject(thisObject).call("setPositions", classTypeNames ,arg_0);
	}

	@Test
	public void testsetPositions_1() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(1);
		boolean arg_0 = true;

		String classTypeNames[] = {"boolean"};

		new ProxyObject(thisObject).call("setPositions", classTypeNames ,arg_0);
	}
}

