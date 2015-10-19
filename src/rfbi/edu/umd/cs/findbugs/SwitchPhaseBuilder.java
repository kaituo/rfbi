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

package edu.umd.cs.findbugs;

import java.io.File;

import edu.umd.cs.findbugs.config.UserPreferences;
import edu.umd.cs.findbugs.detect.BuildInterproceduralCallGraph;

/**
 * @author kaituo
 */
public class SwitchPhaseBuilder {

    /**
     *
     * @param args: starting at 0th element, all of them are paths of jar files to analyze
     */
    public static void main(String[] args) {
        SwitchPhaseBuilder cgbuilder = new SwitchPhaseBuilder();
        int arg = 0;

        while (arg < args.length) {
            String option = args[arg];

            if (!option.startsWith("-")) {
                break;
            }
            arg++;
        }

        cgbuilder.createCallGraph(args, arg);
        //ApplicationCallGraph.getInstance().getCallers("java/lang/Object", "hashCode", "()I", false);
    }

    private void createCallGraph(String[] files, int startIndex) {
        // Create temporary directory in filesystem
        //File tmpdir = RFBIUtil.createFolder("callGraphBuilder");

        //File tmpfile = null;
        if(files.length-startIndex < 1) {
            return;
        }

        try {
            // Create a class file to analyze
            //tmpfile = createEmptyClassFile(tmpdir);

            // Unfortunately there's quite a bit of gobbledygook required
            // to set up a FindBugs2.

            FindBugs2 engine = new FindBugs2();

            engine.setBugReporter(new PrintingBugReporter());

            // Analyze the temporary directory we just created
            Project project = new Project();
            //project.addFile(tmpdir.getAbsolutePath());
            for (int i = startIndex; i < files.length; ++i) {
                project.addFile(files[i]);
            }

            engine.setProject(project);
            PluginLoader fakeLoader = new PluginLoader(true, new File("fakeFile").toURL());
            Plugin fakePlugin = new Plugin("edu.umd.cs.findbugs.fakeplugin", null, null, fakeLoader, true, false);

            DetectorFactoryCollection dfc = new DetectorFactoryCollection(fakePlugin);
            DetectorFactoryCollection.resetInstance(dfc);

            DetectorFactory detectorFactory = new DetectorFactory(fakePlugin, BuildInterproceduralCallGraph.class.getName(),
                    BuildInterproceduralCallGraph.class, true, "slow", "", "");
            fakePlugin.addDetectorFactory(detectorFactory);
            dfc.registerDetector(detectorFactory);
            if (!dfc.factoryIterator().hasNext() || fakePlugin.getDetectorFactories().isEmpty()) {
                throw new IllegalStateException();
            }

            engine.setDetectorFactoryCollection(dfc);

            engine.setUserPreferences(UserPreferences.createDefaultUserPreferences());



            engine.execute();


        } catch (Throwable t) {
            t.printStackTrace();
        } finally {

            DetectorFactoryCollection.resetInstance(null);

        }
    }

}
