package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class BranchHandle
{
 public static void testsetInstruction(org.apache.bcel.generic.Instruction a) {
        ((org.apache.bcel.generic.BranchHandle) DeSerializedState.getInstance().getDeserializedObj()).setInstruction(a);
 }


}