package edu.umass.cs.rfbi.cg;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import edu.umass.cs.rfbi.callgraph.ApplicationCallGraph;
import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.RFBIUtil;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;

/**
 * @author Kaituo
 */
public class SwitchAspectsGenerator {
    private int HEPj;
    private final String HEDir;
    //    public RFile rf;
    //
    public SwitchAspectsGenerator() {
        HEPj = 0;
        HEDir = Config.getInstance().getStringProperty("he.codegen.switch");
    }

    //    public void dyCheck(BugInstance bi) { }
    //
    //    public void dyCheck(String bi, String foul, String pN) { }
    //
    //    public void dyCheck(BugInstance bi, String foul, String pN) { }

    protected void generateSwitchPhase(String className, String methodName, String packageName, String prefix, int index, String dir)
            throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("package ");
        sb.append(packageName);
        sb.append(";");
        sb.append("\n\n");
        sb.append("import edu.umass.cs.rfbi.util.TraceWriter;\n");
        sb.append("\npublic aspect ");
        sb.append(prefix);
        sb.append(index);
        sb.append(" {");
        sb.append("\n");


        sb.append("\tbefore(");
        sb.append(className);
        sb.append(" instance): call(* ");
        sb.append(className);
        sb.append("+.");
        sb.append(methodName);
        sb.append("(..)) && this(instance); {");
        sb.append("\n\t\tTraceWriter.writeState(instance);");
        sb.append("\n\t}");
        sb.append("\n}");

        StringBuffer filetoWrite = new StringBuffer();
        filetoWrite.append(dir);
        filetoWrite.append("/");
        filetoWrite.append(prefix);
        filetoWrite.append(index);
        filetoWrite.append(".aj");
        String fileName = filetoWrite.toString();
        RFBIUtil.createFile(fileName);

        RFBIUtil.write(sb.toString(), fileName);
    }

    /**
     * TODO
     * @param f
     * @return
     */
    private String[][] analyze(File f) {
        return null;
    }

    public void generateAllSwitchAspects() {
        assert Config.getInstance().getBooleanProperty("switch.enabled");
        if(Config.getInstance().getBooleanProperty("he.switch.phase")) {
            // Incomplete analyze
            //String[][] HEPerms = analyze(null);
            //generateSwitchAspects(HEPerms, "edu.umass.cs.rfbi.he", "HE");
            ApplicationCallGraph.getInstance().getCallers("java/lang/Object", "hashCode", "()I", false);
        }
    }

    /**
     *
     * @param perms: perm[i][0]: class name; perm[i][1]: method name; perm[i][2]: method signature; perm[i][4]: static or not
     * e.g. perm[i][0]: "java/lang/Object"; perm[i][1]: "hashCode"; perm[i][2]: "()I"; perm[i][3]: false
     */
    private void generateSwitchAspects(String[][] perms, String packageName, String filePrefix) {
        for(int i=0; i<perms.length; i++) {
            Set<InterproceduralCallGraphVertex> callers =
                    ApplicationCallGraph.getInstance().getCallers(perms[i][0], perms[i][1], perms[i][2], Boolean.valueOf(perms[i][3]));
            generateSwitchAspectJ(callers, packageName, filePrefix);
        }
    }

    /**
     *
     * @param callers:  all application callers of a perm
     * @param packageName: e.g. "edu.umass.cs.rfbi.he"
     * @param filePrefix: "HE"
     */
    private void generateSwitchAspectJ(Set<InterproceduralCallGraphVertex> callers, String packageName, String filePrefix) {
        for(InterproceduralCallGraphVertex caller: callers) {
            try {
                XMethod xmethod = caller.getXmethod();
                //String[] names = RFBIUtil.splitFullMethodName(caller.getXmethod().toString());
                generateSwitchPhase(xmethod.getClassName(), xmethod.getName(), packageName, filePrefix, HEPj++, HEDir);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }
}
