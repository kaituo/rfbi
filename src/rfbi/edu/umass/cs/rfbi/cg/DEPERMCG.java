package edu.umass.cs.rfbi.cg;

import java.io.IOException;

import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.RFBIUtil;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.util.ClassName;

/**
 * @author kaituo
 */
public class DEPERMCG implements PERMCG {
    public String d2RFile, d2RDir;
    //public static String filePublic;
    private static DEPERMCG instance = null;
    private int pj;

    protected DEPERMCG() {
        super();
        /*Properties userConfigValues = loadAndApplyProperties("rfbi.mf");
        d2RDir = userConfigValues.getProperty("de.codegen.folder");
        d2RFile = userConfigValues.getProperty("de.runtime.record");*/
        //filePublic = "./DyFile/DE/DE2.aj";
        d2RDir = Config.getInstance().getStringProperty("de.codegen.folder");
        d2RFile = Config.getInstance().getStringProperty("de.runtime.record");
        RFBIUtil.createFolder(d2RDir);
        RFBIUtil.createFile(d2RFile);
        //create(filePublic);
        pj = 1;
    }

    public static DEPERMCG getInstance() {
        if(instance == null) {
            instance = new DEPERMCG();
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
    //
    //    public Set<String> readRFile(String fileName) throws FileNotFoundException {
    //        Set<String> delete = new HashSet<String>();
    //
    //        Scanner dscanner = new Scanner(new FileInputStream(fileName));
    //
    //        try {
    //            while (dscanner.hasNextLine()){
    //                delete.add(dscanner.nextLine());
    //            }
    //        }
    //        finally {
    //            dscanner.close();
    //        }
    //
    //        return delete;
    //    }

    public String generatePart1(boolean isPublic, int i, String cP) {
        StringBuffer sb = new StringBuffer();
        sb.append("package ");
        sb.append(cP);
        sb.append(";");
        sb.append("\n\n");
        sb.append("import edu.umass.cs.rfbi.util.RFile;\n");
        sb.append("\npublic aspect DE");
        sb.append(i);
        sb.append(" {");
        sb.append("\n");

        return sb.toString();
    }

    public String generatePart2(boolean isPublic, String callStr, String exceptionStr, int i) {
        StringBuffer sb = new StringBuffer();
        sb.append("\tpointcut concernedExeExc");
        sb.append("()");
        sb.append("\n\t: !within(DE");
        sb.append(i);
        sb.append(") && (cflow(execution(* ");
        sb.append(callStr);
        sb.append(")) && handler(");
        sb.append(exceptionStr);
        sb.append("+));");

        return sb.toString();
    }

    public String generatePart3(boolean isPublic, String bugLoc) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n\n\tbefore() : concernedExeExc");
        sb.append("() {");
        sb.append("\n\t\tRFile.writeDE2(\"");
        sb.append(bugLoc);
        sb.append("\", ");
        sb.append("\"");
        sb.append(d2RFile);
        sb.append("\");");
        sb.append("\n\t}");
        sb.append("\n");
        //        String ajcode3 = "\n\n\tbefore() : concernedExeExc" + "() {" +
        //                "\n\t\twriteDE2(\"" + callStr + "\", " +
        //                "\"" +
        //                d2RFile +
        //                "\");" +
        //                "\n\t}" +
        //                "\n";

        return sb.toString();
    }

    //    private Method getMethod(XMethod called) {
    //        Class classInst = null;
    //        try {
    //            classInst = Class.forName(called.getClassDescriptor().getDottedClassName());
    //        } catch (ClassNotFoundException e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        if(classInst!=null) {
    //            Method[] allMethods = classInst.getDeclaredMethods();
    //            for (Method m : allMethods) {
    //                String mname = m.getName();
    //                if(mname.equals(called.getName())) {
    //                    return m;
    //                }
    //            }
    //        }
    //
    //        return null;
    //    }

    public void generateAspectJ(XMethod called, String causeName, String bugLoc) {
        assert(Config.getInstance().getBooleanProperty("perm.enabled"));
        assert(Config.getInstance().getBooleanProperty("de.perm.phase"));

        String[] throwns = called.getThrownExceptions();

        if(throwns==null || throwns.length==0) {
            return;
        }

        boolean found = false;
        ClassDescriptor calledDP = DescriptorFactory.instance().getClassDescriptor(ClassName.toSlashedClassName(causeName));
        for(String e: throwns) {
            ClassDescriptor eDP = DescriptorFactory.instance().getClassDescriptor(e);
            try {
                if(AnalysisContext.currentAnalysisContext().getSubtypes2()
                        .isSubtype(eDP, calledDP)) {
                    found = true;
                    break;
                }
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if(!found) {
            return;
        }


        // method info
        // Any "called" method in a class or its subclass
        String c = called.getClassName() + "+." + called.getName() + "(..)";
        StringBuffer publicPointCut = new StringBuffer();
        publicPointCut.append(generatePart1(true, pj, "edu.umass.cs.rfbi.de"));
        publicPointCut.append(generatePart2(true, c, causeName, pj));
        publicPointCut.append(generatePart3(true, bugLoc));

        publicPointCut.append("}");

        StringBuffer filetoWrite = new StringBuffer();
        filetoWrite.append(d2RDir);
        filetoWrite.append("/DE");
        filetoWrite.append(pj);
        filetoWrite.append(".aj");
        String fileName = filetoWrite.toString();

        RFBIUtil.createFile(fileName);

        try {
            RFBIUtil.write(publicPointCut.toString(), fileName);
            pj++;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

