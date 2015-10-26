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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
     * @param fmeth
     *            className.methodName
     * @return [className, methodName]
     */
    public static String[] splitFullMethodName(String fmeth) {
        int lastOccur = fmeth.lastIndexOf(".");
        return new String[] { fmeth.substring(0, lastOccur), fmeth.substring(lastOccur + 1) };

    }

    public static File createFolder(String dir) {
        File f = null;
        boolean bool = false;

        try {
            // create new folders
            f = new File(dir);

            // tests if folder exists
            bool = f.exists();

            // prints
            // System.out.println("File exists: "+bool);

            if (bool == true) {
                // delete() invoked
                FileUtils.deleteDirectory(f);
            }
            // create new file in the system
            f.mkdirs();
            return f;
        } catch (Exception e) {
            AnalysisContext.logError("Cannot create folders.");
            assert false;
        }
        return null;
    }

    public static File createFile(String file) {
        File f = null;
        boolean bool = false;

        try {
            // create new files
            f = new File(file);

            // tests if file exists
            bool = f.exists();

            // prints
            // System.out.println("File exists: "+bool);

            if (bool == true) {
                // delete() invoked
                f.delete();
            }
            // create new file in the system
            f.createNewFile();
            return f;
        } catch (Exception e) {
            AnalysisContext.logError("Cannot create file " + file);
            assert false;
        }
        return null;
    }

    // public void dyCheck(BugInstance bi) { }
    //
    // public void dyCheck(String bi, String foul, String pN) { }
    //
    // public void dyCheck(BugInstance bi, String foul, String pN) { }

    public String getPackageName(String foul, boolean slash) {
        if (slash) {
            int lastOne = foul.lastIndexOf("/");
            return foul.substring(0, lastOne + 1);
        } else {
            int lastOne = foul.lastIndexOf(".");
            return foul.substring(0, lastOne);
        }

    }

    public String getPackageNameSlash(String foul) {
        int lastOne = foul.lastIndexOf("/");
        return foul.substring(0, lastOne + 1);
    }

    /** Write fixed content to the given file. */
    public static void write(String code, String ajFileName) throws IOException {
        Writer out = new OutputStreamWriter(new FileOutputStream(ajFileName));
        try {
            out.write(code);
        } finally {
            out.close();
        }
    }

    public static void append(String content, String fileName) {
        BufferedWriter bw = null;

        try {
            // APPEND MODE SET HERE
            bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(content);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        } // end try/catch/finally
    }

    /**
     * Returns the difference of two sets. The returned set contains all elements that are contained by {@code set1} and
     * not contained by {@code set2}.
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> difference(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();

        for(String a: set1) {
            if(!set2.contains(a)) {
                res.add(a);
            }
        }

        return res;
    }

    public static void printAndGetSize(Set<String> union) {
        Iterator<String> iterator = union.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        // Get size of a collection
        int size = union.size();
        if (union.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("size: " + size);
        }
        System.out.println();
    }

    /**
     * Read file contents line-by-line into a set
     * @param fin
     * @return
     * @throws IOException
     */
    public static Set<String> readFile2Set(File fin)  {

        Set<String> res = new HashSet<>();
        //Construct BufferedReader from InputStreamReader
        BufferedReader br = null;

        String line = null;
        try {
            FileInputStream fis = new FileInputStream(fin);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                res.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return res;
    }

}
