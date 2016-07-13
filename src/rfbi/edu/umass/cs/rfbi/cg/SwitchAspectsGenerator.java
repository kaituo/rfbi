package edu.umass.cs.rfbi.cg;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import edu.umass.cs.rfbi.callgraph.ApplicationCallGraph;
import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.JSON;
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
        instanceRecords = HESwitchDir+"/" + Config.INSTANCE_RECORDS_FILE;
        staticRecords = HESwitchDir+"/" + Config.STATIC_RECORDS_FILE;
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

    }


    public void generateAllSwitchAspects() {
        assert Config.getInstance().getBooleanProperty("switch.enabled");
        if(Config.getInstance().getBooleanProperty("he.switch.phase")) {
            // Find Unconfirmed perms
            Set<String> allRecords = RFBIUtil.readFile2Set(new File(HEPERMDir + "/" + Config.ALL_RECORDS_FILE));
            Set<String> permRecords = RFBIUtil.readFile2Set(new File(HEPERMDir + "/" + Config.RUNTIME_FILE));
            Set<String> leftOverPerms = RFBIUtil.difference(allRecords, permRecords);
            // Generate switches for unconfirmed persm
            // generateHESwitch(leftOverPerms, "edu.umass.cs.rfbi.he", "HE");
            Set<InterproceduralCallGraphVertex> allCallers = ApplicationCallGraph.getInstance().getCallers("java/lang/Object", "hashCode", "()I", false);

            if(SystemProperties.getBoolean("rfbi.callgraph.debug")) {
                return;
            }

            generateSwitchAspectJ(allCallers, "edu.umass.cs.rfbi.he", "HE");

            // copy unconfirmed perms to its directory
            String unconfirmedPermDir = Config.getInstance().getStringProperty("he.codegen.leftperm");
            RFBIUtil.createFolder(unconfirmedPermDir);

            File dest = new File(unconfirmedPermDir);
            String allPermDir = Config.getInstance().getStringProperty("he.codegen.perm");
            JSONObject records = (JSONObject) JSON.readJSONLog(Config.getInstance().getStringProperty("he.codegen.perm") + "/" + Config.LOG_STREAM_FILE);

            for(String slashedClassName: leftOverPerms) {
                StringBuffer sb = new StringBuffer();
                sb.append(allPermDir);
                sb.append("/HE");
                sb.append(Long.toString((Long)(records.get(slashedClassName))));
                sb.append(".aj");
                File source = new File(sb.toString());
                try {
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //                HEPERMCG.getInstance(true).generatePERMAspectJ(slashedClassName.replace('/', '.'), slashedClassName);
            }
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
                String sig = xmethod.getSignature();

                if(xmethod.isStatic()) {
                    // make a record of static method
                    RFBIUtil.append(className + "." + methName + " " + sig, staticRecords);
                } else {
                    generateSwitchPhase(className, methName, packageName, filePrefix, HEPj++, HESwitchDir);
                    // make a record of all the blacklist classes
                    RFBIUtil.append(className + "." + methName + " " + sig, instanceRecords);
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }
}
