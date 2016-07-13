package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class LineNumberTable
{
 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.LineNumberTable) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }

 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.LineNumberTable) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}