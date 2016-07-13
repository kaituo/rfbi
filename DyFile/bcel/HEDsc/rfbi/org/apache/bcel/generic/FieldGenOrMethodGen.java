package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class FieldGenOrMethodGen
{
 public static void testsetType(org.apache.bcel.generic.Type a) {
        ((org.apache.bcel.generic.FieldGenOrMethodGen) DeSerializedState.getInstance().getDeserializedObj()).setType(a);
 }

 public static void testremoveAttribute(org.apache.bcel.classfile.Attribute a) {
        ((org.apache.bcel.generic.FieldGenOrMethodGen) DeSerializedState.getInstance().getDeserializedObj()).removeAttribute(a);
 }


}