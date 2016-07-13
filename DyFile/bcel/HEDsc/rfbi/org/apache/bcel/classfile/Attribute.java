package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class Attribute
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.Attribute) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}