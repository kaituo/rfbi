package edu.umass.cs.rfbi.cg;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import edu.umass.cs.rfbi.callgraph.ApplicationCallGraph;
import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.RFBIUtil;
import edu.umd.cs.findbugs.SystemProperties;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;

/**
 * @author Kaituo
 */
public class SwitchAspectsGenerator {
    private int HEPj;
    private final String HESwitchDir, HEPERMDir, HEStateDir, instanceRecords, staticRecords;

    public static final boolean DEBUG = SystemProperties.getBoolean("rfbi.SwitchAspectsGenerator.debug");

    public SwitchAspectsGenerator() {
        HEPj = 0;
        HESwitchDir = Config.getInstance().getStringProperty("he.codegen.switch");
        HEPERMDir = Config.getInstance().getStringProperty("he.codegen.perm");
        HEStateDir = Config.getInstance().getStringProperty("he.save.state");
        instanceRecords = HESwitchDir+"/instanceRecords.txt";
        staticRecords = HESwitchDir+"/staticRecords.txt";
        RFBIUtil.createFolder(HESwitchDir);
        RFBIUtil.createFolder(HEStateDir);
        RFBIUtil.createFile(instanceRecords);
        RFBIUtil.createFile(staticRecords);
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
        sb.append("\nprivileged aspect ");
        sb.append(prefix);
        sb.append(index);
        sb.append(" {");
        sb.append("\n");


        sb.append("\tbefore(");
        sb.append(className);
        sb.append(" instance): execution(* ");
        //sb.append(className);
        //sb.append("+.");
        sb.append(methodName);
        sb.append("(..)) && this(instance) {");
        sb.append("\n\t\tTraceWriter.writeState(instance, \"");
        sb.append(className);
        sb.append(".");
        sb.append(methodName);
        sb.append("\", \"");
        sb.append(HEStateDir);
        sb.append("\");");
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
        // make a record of all the blacklist classes
        RFBIUtil.append(className+"."+methodName, instanceRecords);
    }


    public void generateAllSwitchAspects() {
        assert Config.getInstance().getBooleanProperty("switch.enabled");
        if(Config.getInstance().getBooleanProperty("he.switch.phase")) {
            // Incomplete analyze
            Set<String> allRecords = RFBIUtil.readFile2Set(new File(HEPERMDir+"/allRecords.txt"));
            Set<String> permRecords = RFBIUtil.readFile2Set(new File(HEPERMDir+"/runtime.txt"));
            Set<String> leftOverPerms = RFBIUtil.difference(allRecords, permRecords);
            generateHESwitch(leftOverPerms, "edu.umass.cs.rfbi.he", "HE");
            for(String slashedClassName: leftOverPerms) {
                HEPERMCG.getInstance(true).generatePERMAspectJ(slashedClassName.replace('/', '.'), slashedClassName);
            }
            //ApplicationCallGraph.getInstance().getCallers("java/lang/Object", "hashCode", "()I", false);
        }
    }


    private void generateHESwitch(Set<String> leftOverPerms, String packageName, String filePrefix) {
        Set<InterproceduralCallGraphVertex> allCallers = new HashSet<>();
        for(String perm: leftOverPerms) {
            Set<InterproceduralCallGraphVertex> callers = ApplicationCallGraph.getInstance().getCallers(perm, "hashCode", "()I", false);
            allCallers.addAll(callers);

            if(DEBUG) {
                System.out.println(callers.size() + " callers are to be genearated.");
            }
        }
        if(DEBUG) {
            System.out.println(allCallers.size() + " callers altogether");
        }
        generateSwitchAspectJ(allCallers, packageName, filePrefix);
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
                String className = xmethod.getClassName();
                String methName = xmethod.getName();
                // make a record of static method
                if(xmethod.isStatic()) {
                    RFBIUtil.append(className+"."+methName, staticRecords);
                } else {
                    generateSwitchPhase(className, methName, packageName, filePrefix, HEPj++, HESwitchDir);
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }
}
