package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class InnerClass
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.InnerClass) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}