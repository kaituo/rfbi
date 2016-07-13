package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class ConstantNameAndType
{
 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.ConstantNameAndType) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }

 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.ConstantNameAndType) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}