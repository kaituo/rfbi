package rfbi.org.apache.bcel.classfile;

import edu.umass.cs.rfbi.DeSerializedState;

public class Method
{
 public static void testaccept(org.apache.bcel.classfile.Visitor a) {
        ((org.apache.bcel.classfile.Method) DeSerializedState.getInstance().getDeserializedObj()).accept(a);
 }


}