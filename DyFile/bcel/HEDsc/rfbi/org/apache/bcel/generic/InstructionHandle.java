package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class InstructionHandle
{
 public static void testaddTargeter(org.apache.bcel.generic.InstructionTargeter a) {
        ((org.apache.bcel.generic.InstructionHandle) DeSerializedState.getInstance().getDeserializedObj()).addTargeter(a);
 }

 public static void testsetInstruction(org.apache.bcel.generic.Instruction a) {
        ((org.apache.bcel.generic.InstructionHandle) DeSerializedState.getInstance().getDeserializedObj()).setInstruction(a);
 }


}