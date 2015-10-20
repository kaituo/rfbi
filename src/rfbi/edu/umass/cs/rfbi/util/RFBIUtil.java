/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2003-2008 University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umass.cs.rfbi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;

import edu.umd.cs.findbugs.ba.AnalysisContext;

/**
 * @author kaituo
 */
public class RFBIUtil {
    private static String tempdir = null;
    /**
     * return system tmp directory
     *
     * @return
     */
    public static String getTmpDirectory() {
        if (tempdir != null) {
            return tempdir;
        }

        tempdir = System.getProperty("java.io.tmpdir");
        if (!(tempdir.endsWith("/") || tempdir.endsWith("\\"))) {
            tempdir = tempdir + System.getProperty("file.separator");
        }

        return tempdir;
    }

    /**
     *
     * @param fmeth className.methodName
     * @return [className, methodName]
     */
    public static String[] splitFullMethodName(String fmeth) {
        int lastOccur = fmeth.lastIndexOf(".");
        return new String[] {fmeth.substring(0, lastOccur), fmeth.substring(lastOccur+1)};

    }

    public static File createFolder(String dir) {
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
            return f;
        }catch(Exception e){
            AnalysisContext.logError("Cannot create folders.");
            assert false;
        }
        return null;
    }

    public static File createFile(String file) {
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
            return f;
        }catch(Exception e){
            AnalysisContext.logError("Cannot create a file.");
            assert false;
        }
        return null;
    }

    //    public void dyCheck(BugInstance bi) { }
    //
    //    public void dyCheck(String bi, String foul, String pN) { }
    //
    //    public void dyCheck(BugInstance bi, String foul, String pN) { }

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

    /** Write fixed content to the given file. */
    public static void write(String code, String ajFileName) throws IOException  {
        Writer out = new OutputStreamWriter(new FileOutputStream(ajFileName));
        try {
            out.write(code);
        } finally {
            out.close();
        }
    }
}
