package edu.umass.cs.rfbi.cg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;

import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.RFBIUtil;

/**
 * @author kaituo
 */
public class HEPERMCG implements PERMCG {
    public String h2RDir, runtimeFile, allRecordFile;
    //public static String filePublic;
    private static HEPERMCG instance = null;
    private int pj;
    JSONObject obj = new JSONObject();
    //    private final String logStreamFile;

    /**
     * When generating aspects for unconfirmed bugs, I don't need to recreate
     * @param recreate: Whether I want to recreate the file folder or not
     */
    protected HEPERMCG() {//boolean leftOverPerm
        //        if(leftOverPerm) {
        //            h2RDir = Config.getInstance().getStringProperty("he.codegen.leftperm");
        //        } else {
        h2RDir = Config.getInstance().getStringProperty(Config.HE_PERM_FOLDER);
        //        }
        RFBIUtil.createFolder(h2RDir);
        runtimeFile = h2RDir+"/" + Config.RUNTIME_FILE;
        RFBIUtil.createFile(runtimeFile);
        allRecordFile = h2RDir +"/" + Config.ALL_RECORDS_FILE;
        RFBIUtil.createFile(allRecordFile);
        //        logStreamFile = "/logstream.txt";

        pj = 1;
    }

    public static HEPERMCG getInstance() {//boolean leftOverPerm
        if(instance == null) {
            instance = new HEPERMCG();//leftOverPerm
        }
        return instance;
    }

    //    public static void writeDE(String destr, String fileName) {
    //        FileWriter fstream;
    //
    //        try {
    //            fstream = new FileWriter(fileName,true);
    //            BufferedWriter out = new BufferedWriter(fstream);
    //            out.write(destr);
    //            out.newLine();
    //            out.close();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }

    public Set<String> readRFile(String fileName) throws FileNotFoundException {
        Set<String> delete = new HashSet<String>();

        Scanner dscanner = new Scanner(new FileInputStream(fileName));

        try {
            while (dscanner.hasNextLine()){
                delete.add(dscanner.nextLine());
            }
        }
        finally {
            dscanner.close();
        }

        return delete;
    }

    public String generatePERMPart1(int i, String cP) {
        StringBuffer sb = new StringBuffer();
        sb.append("package ");
        sb.append(cP);
        sb.append(";");
        sb.append("\n\n");
        sb.append("import edu.umass.cs.rfbi.util.RFile;\n");
        sb.append("\npublic aspect HE");
        sb.append(i);
        sb.append(" {");
        sb.append("\n");

        return sb.toString();
    }

    public String generatePERMPart2(String dottedClassName, String slashedClassName) {
        StringBuffer sb = new StringBuffer();
        sb.append("\tpublic int ");
        sb.append(dottedClassName);
        sb.append(".hashCode() {\n");
        sb.append("\t\tRFile.writeDE2(\"");
        sb.append(slashedClassName);
        sb.append("\", \"");
        sb.append(runtimeFile);
        sb.append("\");\n");
        sb.append("\t\treturn super.hashCode();\n");
        sb.append("\t}\n");
        sb.append("}\n");
        return sb.toString();
    }

    public void generatePERMAspectJ(String dottedClassName, String slashedClassName) {


        // keep a record of which aspect file maps to which perm
        // so that later we can search files of unconfirmed perms
        // that need to be copied to re-instrument benchmark programs.
        obj.put(slashedClassName, pj);

        // method info
        StringBuffer publicPointCut = new StringBuffer();
        publicPointCut.append(generatePERMPart1(pj, "edu.umass.cs.rfbi.he"));
        publicPointCut.append(generatePERMPart2(dottedClassName, slashedClassName));
        StringBuffer filetoWrite = new StringBuffer();
        filetoWrite.append(h2RDir);
        filetoWrite.append("/HE");
        filetoWrite.append(pj++);
        filetoWrite.append(".aj");
        String fileName = filetoWrite.toString();
        RFBIUtil.createFile(fileName);

        try {
            RFBIUtil.write(publicPointCut.toString(), fileName);

            // make a record of all the blacklist classes
            RFBIUtil.append(slashedClassName, allRecordFile);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public JSONObject getJSONObj() {
        return obj;
    }
}
