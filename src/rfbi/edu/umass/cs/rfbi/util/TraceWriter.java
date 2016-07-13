package edu.umass.cs.rfbi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;

/**
 * @author kaituo
 */
public class TraceWriter {
    public static File writeState(Object receiver, String name, String dir) {
        File traceFile = null;
        OutputStreamWriter fw;
        try {
            // a new file rfbia.b.c$xxx.trace.gz will be created
            // a.b.c is the class name
            //traceFile = File.createTempFile("rfbi"+receiver.getClass().getName()+"$", ".trace.gz", new File(
            //       getTmpDirectory()));
            traceFile = File.createTempFile(name+"$", ".trace.gz", new File(dir));
            assert (traceFile != null);

            fw = new OutputStreamWriter(new GZIPOutputStream(
                    new FileOutputStream(traceFile)));

            Serializer.storeObject(receiver, fw);

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return traceFile;
    }

}
