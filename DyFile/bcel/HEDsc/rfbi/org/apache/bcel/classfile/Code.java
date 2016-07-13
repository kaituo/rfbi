package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class Code
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.Code) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }

 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.Code) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }


}