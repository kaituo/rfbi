package rfbi.org.apache.bcel.util;

import edu.umass.cs.rfbi.DeSerializedState;

public class ClassPath
{
 public static void testgetInputStream(java.lang.String a, java.lang.String b) throws java.io.IOException {
        ((org.apache.bcel.util.ClassPath) DeSerializedState.getInstance().getDeserializedObj()).getInputStream(a, b);
 }

 public static void testgetInputStream(java.lang.String a) throws java.io.IOException {
        ((org.apache.bcel.util.ClassPath) DeSerializedState.getInstance().getDeserializedObj()).getInputStream(a);
 }

 public static void testgetClassFile(java.lang.String a, java.lang.String b) throws java.io.IOException {
        ((org.apache.bcel.util.ClassPath) DeSerializedState.getInstance().getDeserializedObj()).getClassFile(a, b);
 }

 public static void testgetClassFile(java.lang.String a) throws java.io.IOException {
        ((org.apache.bcel.util.ClassPath) DeSerializedState.getInstance().getDeserializedObj()).getClassFile(a);
 }


}