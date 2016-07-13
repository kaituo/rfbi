package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class ConstantInteger
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.ConstantInteger) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}