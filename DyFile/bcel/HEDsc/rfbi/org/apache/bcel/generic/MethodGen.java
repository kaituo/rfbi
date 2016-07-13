package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class MethodGen
{
 public static void testaddLocalVariable(java.lang.String a, org.apache.bcel.generic.Type b, int c, org.apache.bcel.generic.InstructionHandle d, org.apache.bcel.generic.InstructionHandle e) {
        ((org.apache.bcel.generic.MethodGen) DeSerializedState.getInstance().getDeserializedObj()).addLocalVariable(a, b, c, d, e);
 }

 public static void testaddLocalVariable(java.lang.String a, org.apache.bcel.generic.Type b, org.apache.bcel.generic.InstructionHandle c, org.apache.bcel.generic.InstructionHandle d) {
        ((org.apache.bcel.generic.MethodGen) DeSerializedState.getInstance().getDeserializedObj()).addLocalVariable(a, b, c, d);
 }

 public static void testremoveCodeAttribute(org.apache.bcel.classfile.Attribute a) {
        ((org.apache.bcel.generic.MethodGen) DeSerializedState.getInstance().getDeserializedObj()).removeCodeAttribute(a);
 }


}