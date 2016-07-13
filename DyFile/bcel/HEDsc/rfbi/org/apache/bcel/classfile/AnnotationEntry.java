package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class AnnotationEntry
{
 public static void testdump(java.io.DataOutputStream a) throws java.io.IOException {
        ((org.apache.bcel.classfile.AnnotationEntry) DeSerializedState.getInstance().getDeserializedObj()).dump(a);
 }


}