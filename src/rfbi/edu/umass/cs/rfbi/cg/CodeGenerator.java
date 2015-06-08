package edu.umass.cs.rfbi.cg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import edu.umass.cs.rfbi.util.RFile;
import edu.umd.cs.findbugs.BugInstance;

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

    public void create(String file) {
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
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        }
    }

    public Properties loadAndApplyProperties(final String fileName)
    {
        final Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));

            return properties;
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
            return null; // could not apply configuration file
        }
    }
}
