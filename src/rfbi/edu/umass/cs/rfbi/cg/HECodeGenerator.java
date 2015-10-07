package edu.umass.cs.rfbi.cg;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;

/**
 * @author kaituo
 */
public class HECodeGenerator extends CodeGenerator {
    public String h2RFile, h2RDir;
    //public static String filePublic;
    private static HECodeGenerator instance = null;
    private int pj;

    protected HECodeGenerator() {
        super();
        /*Properties userConfigValues = loadAndApplyProperties("rfbi.mf");*/
        h2RDir = Config.getInstance().getProperty("he.codegen.folder");
        h2RFile = Config.getInstance().getProperty("he.runtime.record");
        if(!createFolder(h2RDir) || !createFile(h2RFile)) {
            AnalysisContext.logError("Cannot create folders or files for HE code generation.");
            assert false;
        }
        pj = 1;
    }

    public static HECodeGenerator getInstance() {
        if(instance == null) {
            instance = new HECodeGenerator();
        }
        return instance;
    }

    public static void writeDE(String destr, String fileName) {
        FileWriter fstream;

        try {
            fstream = new FileWriter(fileName,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(destr);
            out.newLine();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
        sb.append("\t\tRFile.writeDE2(\"the hashCode method of ");
        sb.append(dottedClassName);
        sb.append(" is called\", \"");
        sb.append(h2RFile);
        sb.append("\");\n");
        sb.append("\t\treturn super.hashCode();\n");
        sb.append("\t}\n");
        sb.append("}\n");
        return sb.toString();
    }

    public void generatePERMAspectJ(String dottedClassName) {
        // method info
        StringBuffer publicPointCut = new StringBuffer();
        publicPointCut.append(generatePERMPart1(pj++, "edu.umass.cs.rfbi.he"));
        publicPointCut.append(generatePERMPart2(dottedClassName));

        publicPointCut.append("}");

        StringBuffer filetoWrite = new StringBuffer();
        filetoWrite.append(h2RDir);
        filetoWrite.append("/HE");
        filetoWrite.append(pj);
        filetoWrite.append(".aj");
        String fileName = filetoWrite.toString();
        if(!createFile(fileName)) {
            AnalysisContext.logError("Cannot create file for HE code generation.");
            assert false;
        }

        try {
            write(publicPointCut.toString(), fileName);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void generateSwitchAspectJ(ArrayList<InterproceduralCallGraphVertex> callers) {
        for(InterproceduralCallGraphVertex caller: callers) {
            try {
                XMethod xmethod = caller.getXmethod();
                //String[] names = RFBIUtil.splitFullMethodName(caller.getXmethod().toString());
                generateSwitchPhase(xmethod.getClassName(), xmethod.getName(), "edu.umass.cs.rfbi.he", "HE", pj++, h2RDir);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

}
