package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class LocalVariableInstruction
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.generic.LocalVariableInstruction) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}