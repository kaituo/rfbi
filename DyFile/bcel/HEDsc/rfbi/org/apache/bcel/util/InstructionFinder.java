package rfbi.org.apache.bcel.util;

import edu.umass.cs.rfbi.DeSerializedState;

public class InstructionFinder
{
 public static void testsearch(java.lang.String a, org.apache.bcel.generic.InstructionHandle b) {
        ((org.apache.bcel.util.InstructionFinder) DeSerializedState.getInstance().getDeserializedObj()).search(a, b);
 }

 public static void testsearch(java.lang.String a, org.apache.bcel.generic.InstructionHandle b, org.apache.bcel.util.InstructionFinder.CodeConstraint c) {
        ((org.apache.bcel.util.InstructionFinder) DeSerializedState.getInstance().getDeserializedObj()).search(a, b, c);
 }

 public static void testsearch(java.lang.String a) {
        ((org.apache.bcel.util.InstructionFinder) DeSerializedState.getInstance().getDeserializedObj()).search(a);
 }

 public static void testsearch(java.lang.String a, org.apache.bcel.util.InstructionFinder.CodeConstraint b) {
        ((org.apache.bcel.util.InstructionFinder) DeSerializedState.getInstance().getDeserializedObj()).search(a, b);
 }


}