package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class SourceFile
{
 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.SourceFile) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }

 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.SourceFile) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}