package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class JavaClass
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.JavaClass) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testdump(java.lang.String a) throws java.io.IOException {
        ((org.apache.bcel.classfile.JavaClass) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testdump(java.io.File a) throws java.io.IOException {
        ((org.apache.bcel.classfile.JavaClass) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testdump(java.io.OutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.JavaClass) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.JavaClass) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }


}