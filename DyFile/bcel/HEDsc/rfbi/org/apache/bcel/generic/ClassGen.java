package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class ClassGen
{
 public static void testremoveField(org.apache.bcel.classfile.Field a) {
        ((org.apache.bcel.generic.ClassGen) DeSerializedState.getInstance().getDeserializedObj()).removeField(a);
 }

 public static void testaddEmptyConstructor(int a) {
        ((org.apache.bcel.generic.ClassGen) DeSerializedState.getInstance().getDeserializedObj()).addEmptyConstructor(a);
 }

 public static void testremoveMethod(org.apache.bcel.classfile.Method a) {
        ((org.apache.bcel.generic.ClassGen) DeSerializedState.getInstance().getDeserializedObj()).removeMethod(a);
 }


}