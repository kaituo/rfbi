package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class ConstantPool
{
 public static void testgetConstant(int a, byte b) throws org.apache.bcel.classfile.ClassFormatException {
        ((org.apache.bcel.classfile.ConstantPool) DeSerializedState.getInstance().getDeserializedObj()).getConstant(a, b);
 }

 public static void testgetConstant(int a) {
        ((org.apache.bcel.classfile.ConstantPool) DeSerializedState.getInstance().getDeserializedObj()).getConstant(a);
 }

 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.ConstantPool) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}