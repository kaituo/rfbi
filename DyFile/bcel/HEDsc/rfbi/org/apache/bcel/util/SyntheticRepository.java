package rfbi.org.apache.bcel.util;

import edu.umass.cs.rfbi.DeSerializedState;

public class SyntheticRepository
{
 public static void testloadClass(java.lang.String a) throws java.lang.ClassNotFoundException {
        ((org.apache.bcel.util.SyntheticRepository) DeSerializedState.getInstance().getDeserializedObj()).loadClass(a);
 }

 public static void testloadClass(java.lang.Class a) throws java.lang.ClassNotFoundException {
        ((org.apache.bcel.util.SyntheticRepository) DeSerializedState.getInstance().getDeserializedObj()).loadClass(a);
 }

 public static void testfindClass(java.lang.String a) {
        ((org.apache.bcel.util.SyntheticRepository) DeSerializedState.getInstance().getDeserializedObj()).findClass(a);
 }

 public static void teststoreClass(org.apache.bcel.classfile.JavaClass a) {
        ((org.apache.bcel.util.SyntheticRepository) DeSerializedState.getInstance().getDeserializedObj()).storeClass(a);
 }


}