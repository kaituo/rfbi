package generated_tests;

import org.junit.Test;
import org.junit.BeforeClass;
import edu.umass.cs.rfbi.tracer.*;
import edu.umass.cs.rfbi.tracer.item.*;
import edu.umass.cs.rfbi.generator.*;

public class InstructionList1724768336568513968 {
	private static Object thisObject;

	@BeforeClass
	public static void setUp() throws Exception {
		thisObject = TraceReader.readThisReceiver("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/HEState/org.apache.bcel.generic.InstructionList.append$7385564928402107349.trace.gz");
		TraceReader.readListTraceItem("/home/kaituo/code/rfbi/findbugs/DyFile/bcel/DscState/rfbi.org.apache.bcel.generic.InstructionList.testappend$1724768336568513968.trace.gz");

	}


	@Test
	public void testappend_0() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(0);
		org.apache.bcel.generic.InstructionHandle arg_0 = null;
		org.apache.bcel.generic.BranchInstruction arg_1 = null;

		String classTypeNames[] = {"org.apache.bcel.generic.InstructionHandle",
				"org.apache.bcel.generic.BranchInstruction"};

		new ProxyObject(thisObject).call("append", classTypeNames ,arg_0, arg_1);
	}

	@Test
	public void testappend_1() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(1);
		org.apache.bcel.generic.InstructionHandle arg_0 = null;
		org.apache.bcel.generic.BranchInstruction arg_1 = (org.apache.bcel.generic.BranchInstruction)TraceReader.readObject(1);

		String classTypeNames[] = {"org.apache.bcel.generic.InstructionHandle",
				"org.apache.bcel.generic.BranchInstruction"};

		new ProxyObject(thisObject).call("append", classTypeNames ,arg_0, arg_1);
	}

	@Test
	public void testappend_2() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(2);
		org.apache.bcel.generic.InstructionHandle arg_0 = (org.apache.bcel.generic.InstructionHandle)TraceReader.readObject(0);
		org.apache.bcel.generic.BranchInstruction arg_1 = (org.apache.bcel.generic.BranchInstruction)TraceReader.readObject(1);

		String classTypeNames[] = {"org.apache.bcel.generic.InstructionHandle",
				"org.apache.bcel.generic.BranchInstruction"};

		new ProxyObject(thisObject).call("append", classTypeNames ,arg_0, arg_1);
	}

	@Test
	public void testappend_3() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, java.lang.reflect.InvocationTargetException, ClassNotFoundException {
		TraceReader.setMethodTraceItem(3);
		org.apache.bcel.generic.InstructionHandle arg_0 = (org.apache.bcel.generic.InstructionHandle)TraceReader.readObject(0);
		org.apache.bcel.generic.BranchInstruction arg_1 = (org.apache.bcel.generic.BranchInstruction)TraceReader.readObject(1);

		String classTypeNames[] = {"org.apache.bcel.generic.InstructionHandle",
				"org.apache.bcel.generic.BranchInstruction"};

		new ProxyObject(thisObject).call("append", classTypeNames ,arg_0, arg_1);
	}
}

