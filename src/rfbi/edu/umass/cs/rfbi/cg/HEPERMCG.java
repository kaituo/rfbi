package edu.umass.cs.rfbi.cg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    protected HEPERMCG() {
        super();
        /*Properties userConfigValues = loadAndApplyProperties("rfbi.mf");*/
        h2RDir = Config.getInstance().getStringProperty("he.codegen.perm");
        //h2RFile = Config.getInstance().getStringProperty("he.runtime.record");
        RFBIUtil.createFolder(h2RDir);
        runtimeFile = h2RDir+"/runtime.txt";
        RFBIUtil.createFile(runtimeFile);
        allRecordFile = h2RDir+"/allRecords.txt";
        RFBIUtil.createFile(allRecordFile);

        pj = 1;
    }

    public static HEPERMCG getInstance() {
        if(instance == null) {
            instance = new HEPERMCG();
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

    public String generatePERMPart2(String dottedClassName) {
        StringBuffer sb = new StringBuffer();
        sb.append("\tpublic int ");
        sb.append(dottedClassName);
        sb.append(".hashCode() {\n");
        sb.append("\t\tRFile.writeDE2(\"");
        sb.append(dottedClassName);
        sb.append("\", \"");
        sb.append(runtimeFile);
        sb.append("\");\n");
        sb.append("\t\treturn super.hashCode();\n");
        sb.append("\t}\n");
        sb.append("}\n");
        return sb.toString();
    }

    public void generatePERMAspectJ(String dottedClassName) {
        assert(Config.getInstance().getBooleanProperty("perm.enabled"));
        assert(Config.getInstance().getBooleanProperty("he.perm.phase"));

        // method info
        StringBuffer publicPointCut = new StringBuffer();
        publicPointCut.append(generatePERMPart1(pj, "edu.umass.cs.rfbi.he"));
        publicPointCut.append(generatePERMPart2(dottedClassName));

        //publicPointCut.append("}");

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
            RFBIUtil.append(dottedClassName, allRecordFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
