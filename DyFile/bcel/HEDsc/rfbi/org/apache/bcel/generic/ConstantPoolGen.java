package rfbi.org.apache.bcel.generic;

import edu.umass.cs.rfbi.DeSerializedState;

public class ConstantPoolGen
{
 public static void testlookupUtf8(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupUtf8(a);
 }

 public static void testaddUtf8(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addUtf8(a);
 }

 public static void testlookupClass(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupClass(a);
 }

 public static void testlookupMethodref(org.apache.bcel.generic.MethodGen a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupMethodref(a);
 }

 public static void testlookupMethodref(java.lang.String a, java.lang.String b, java.lang.String c) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupMethodref(a, b, c);
 }

 public static void testlookupNameAndType(java.lang.String a, java.lang.String b) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupNameAndType(a, b);
 }

 public static void testaddClass(org.apache.bcel.generic.ObjectType a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addClass(a);
 }

 public static void testaddClass(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addClass(a);
 }

 public static void testaddMethodref(org.apache.bcel.generic.MethodGen a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addMethodref(a);
 }

 public static void testaddMethodref(java.lang.String a, java.lang.String b, java.lang.String c) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addMethodref(a, b, c);
 }

 public static void testaddNameAndType(java.lang.String a, java.lang.String b) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addNameAndType(a, b);
 }

 public static void testlookupFieldref(java.lang.String a, java.lang.String b, java.lang.String c) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupFieldref(a, b, c);
 }

 public static void testaddString(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addString(a);
 }

 public static void testaddFieldref(java.lang.String a, java.lang.String b, java.lang.String c) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).addFieldref(a, b, c);
 }

 public static void testlookupString(java.lang.String a) {
        ((org.apache.bcel.generic.ConstantPoolGen) DeSerializedState.getInstance().getDeserializedObj()).lookupString(a);
 }


}