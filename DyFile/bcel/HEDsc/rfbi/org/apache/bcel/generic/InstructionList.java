package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class InstructionList
{
 public static void testappend(org.apache.bcel.generic.Instruction a, org.apache.bcel.generic.Instruction b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.Instruction a) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a);
 }

 public static void testappend(org.apache.bcel.generic.BranchInstruction a) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a);
 }

 public static void testappend(org.apache.bcel.generic.Instruction a, org.apache.bcel.generic.CompoundInstruction b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.InstructionHandle a, org.apache.bcel.generic.CompoundInstruction b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.InstructionHandle a, org.apache.bcel.generic.Instruction b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.InstructionHandle a, org.apache.bcel.generic.BranchInstruction b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.InstructionHandle a, org.apache.bcel.generic.InstructionList b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.Instruction a, org.apache.bcel.generic.InstructionList b) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a, b);
 }

 public static void testappend(org.apache.bcel.generic.InstructionList a) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a);
 }

 public static void testappend(org.apache.bcel.generic.CompoundInstruction a) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).append(a);
 }

 public static void testsetPositions(boolean a) {
        ((org.apache.bcel.generic.InstructionList) DeSerializedState.getInstance().getDeserializedObj()).setPositions(a);
 }


}