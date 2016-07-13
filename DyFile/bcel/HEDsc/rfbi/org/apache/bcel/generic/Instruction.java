package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class Instruction
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.generic.Instruction) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}