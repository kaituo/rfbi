package edu.umass.cs.rfbi.cg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;

import edu.umass.cs.rfbi.util.RFile;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.ba.AnalysisContext;

/**
 * @author Kaituo
 */
public abstract class CodeGenerator {
    public RFile rf;

    public CodeGenerator() {
        rf = new RFile();
    }

    /** Write fixed content to the given file. */
    public void write(String code, String ajFileName) throws IOException  {
        Writer out = new OutputStreamWriter(new FileOutputStream(ajFileName));
        try {
            out.write(code);
        } finally {
            out.close();
        }
    }

    public void dyCheck(BugInstance bi) { }

    public void dyCheck(String bi, String foul, String pN) { }

    public void dyCheck(BugInstance bi, String foul, String pN) { }

    public String getPackageName(String foul, boolean slash) {
        if(slash) {
            int lastOne = foul.lastIndexOf("/");
            return foul.substring(0, lastOne+1);
        } else {
            int lastOne = foul.lastIndexOf(".");
            return foul.substring(0, lastOne);
        }

    }

    public String getPackageNameSlash(String foul) {
        int lastOne = foul.lastIndexOf("/");
        return foul.substring(0, lastOne+1);
    }

    public boolean createFolder(String dir) {
        File f = null;
        boolean bool = false;

        try{
            // create new folders
            f = new File(dir);

            // tests if folder exists
            bool = f.exists();

            // prints
            //System.out.println("File exists: "+bool);

            if(bool == true)
            {
                // delete() invoked
                FileUtils.deleteDirectory(f);
            }
            // create new file in the system
            f.mkdirs();
            return true;
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        }
        return false;
    }

    public boolean createFile(String file) {
        File f = null;
        boolean bool = false;

        try{
            // create new files
            f = new File(file);



            // tests if file exists
            bool = f.exists();

            // prints
            //System.out.println("File exists: "+bool);

            if(bool == true)
            {
                // delete() invoked
                f.delete();
            }
            // create new file in the system
            f.createNewFile();
            return true;
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        }
        return false;
    }

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
        if(!createFile(fileName)) {
            AnalysisContext.logError("Cannot create file for code generation.");
            assert false;
        }

        write(sb.toString(), fileName);
    }
}
