package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class ConstantUtf8
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.ConstantUtf8) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.ConstantUtf8) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }


}