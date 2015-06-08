package edu.umass.cs.rfbi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Kaituo
 */
public class RFile {
    public void createRFile(String fileName) {
        File f = new File(fileName);
        boolean success = false;

        if (f.exists()) {
            success = deleteRFile(f);
            if(!success) {
                throw new IllegalArgumentException("Delete: deletion failed");
            }
        }

        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean deleteRFile(File f) {
        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + f.getName());
        }

        // Attempt to delete it
        return f.delete();
    }

    public static void writeDE2(String cN, String fileName) {
        FileWriter fstream;
        try {
            fstream = new FileWriter(fileName,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(cN);
            out.newLine();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
