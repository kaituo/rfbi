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
import java.util.zip.GZIPOutputStream;

/**
 * @author kaituo
 */
public class TraceWriter {
    /**
     * return system tmp directory
     *
     * @return
     */
    private static String getTmpDirectory() {
        String tempdir = null;

        if (tempdir != null) {
            return tempdir;
        }

        tempdir = System.getProperty("java.io.tmpdir");
        if (!(tempdir.endsWith("/") || tempdir.endsWith("\\"))) {
            tempdir = tempdir + System.getProperty("file.separator");
        }

        return tempdir;
    }

    public static File writeState(Object receiver, String dir) {
        File traceFile = null;
        OutputStreamWriter fw;
        try {
            // a new file rfbia.b.c$xxx.trace.gz will be created
            // a.b.c is the class name
            //traceFile = File.createTempFile("rfbi"+receiver.getClass().getName()+"$", ".trace.gz", new File(
            //       getTmpDirectory()));
            traceFile = File.createTempFile("rfbi"+receiver.getClass().getName()+"$", ".trace.gz", new File(dir));
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
