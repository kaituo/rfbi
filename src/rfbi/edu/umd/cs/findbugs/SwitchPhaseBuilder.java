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

import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.config.UserPreferences;
import edu.umd.cs.findbugs.detect.BuildAllocationSites;
import edu.umd.cs.findbugs.detect.BuildHybridTypeAnalysis;
import edu.umd.cs.findbugs.detect.BuildInterproceduralCallGraph;
import edu.umd.cs.findbugs.detect.BuildRapidTypeAnalysis;
import edu.umd.cs.findbugs.plan.DetectorFactorySelector;
import edu.umd.cs.findbugs.plan.DetectorOrderingConstraint;
import edu.umd.cs.findbugs.plan.SingleDetectorFactorySelector;

/**
 * @author kaituo
 */
public class SwitchPhaseBuilder {
    private volatile CallGraphAnalysisMode mode = CallGraphAnalysisMode.CHA;

    /**
     *
     * [-auxclasspath : separated auxiliary classpath entry] [-main main method] files to analyze
     */
    public static void main(String[] args) {
        SwitchPhaseBuilder cgbuilder = new SwitchPhaseBuilder();
        int arg = 0;
        String[] auxPath = null;
        String mainMethod = null;

        while (arg < args.length) {
            String option = args[arg];

            if (!option.startsWith("-")) {
                break;
            } else if (option.startsWith("-auxclasspath")) {
                arg++;
                auxPath = args[arg].split(":");
            }
            //                else if (option.startsWith("-main")) {
            //                arg++;
            //                mainMethod = args[arg];
            //            }
            arg++;
        }

        cgbuilder.createCallGraph(args, arg, auxPath, mainMethod);
        //ApplicationCallGraph.getInstance().getCallers("java/lang/Object", "hashCode", "()I", false);
    }

    private void createCallGraph(String[] files, int startIndex, String[] auxPath, String mainMethod) {
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

            if(auxPath != null) {
                for (int j=0; j<auxPath.length; j++) {
                    project.addAuxClasspathEntry(auxPath[j]);
                }
            }

            engine.setProject(project);
            PluginLoader fakeLoader = new PluginLoader(true, new File("fakeFile").toURL());
            Plugin fakePlugin = new Plugin("edu.umd.cs.findbugs.fakeplugin", null, null, fakeLoader, true, false);

            DetectorFactoryCollection dfc = new DetectorFactoryCollection(fakePlugin);
            DetectorFactoryCollection.resetInstance(dfc);

            DetectorFactory detectorFactory_cha = new DetectorFactory(fakePlugin, BuildInterproceduralCallGraph.class.getName(),
                    BuildInterproceduralCallGraph.class, true, "slow", "", "");
            fakePlugin.addDetectorFactory(detectorFactory_cha);
            dfc.registerDetector(detectorFactory_cha);

            if (Config.getInstance().getStringProperty("callGraphMode").equals("RTA")) {
                DetectorFactory detectorFactory_allocationSites = new DetectorFactory(fakePlugin, BuildAllocationSites.class.getName(),
                        BuildAllocationSites.class, true, "slow", "", "");
                DetectorFactory detectorFactory_rta = new DetectorFactory(fakePlugin, BuildRapidTypeAnalysis.class.getName(),
                        BuildRapidTypeAnalysis.class, true, "slow", "", "");

                fakePlugin.addDetectorFactory(detectorFactory_allocationSites);
                fakePlugin.addDetectorFactory(detectorFactory_rta);

                dfc.registerDetector(detectorFactory_rta);
                dfc.registerDetector(detectorFactory_allocationSites);
                DetectorFactorySelector chaSelector = new SingleDetectorFactorySelector(fakePlugin, BuildInterproceduralCallGraph.class.getName());
                DetectorFactorySelector rtaSelector = new SingleDetectorFactorySelector(fakePlugin, BuildRapidTypeAnalysis.class.getName());
                DetectorFactorySelector allocSelector = new SingleDetectorFactorySelector(fakePlugin, BuildAllocationSites.class.getName());

                DetectorOrderingConstraint constraint1 = new DetectorOrderingConstraint(chaSelector, rtaSelector);
                //                constraint1.setSingleSource(chaSelector instanceof SingleDetectorFactorySelector);
                fakePlugin.addInterPassOrderingConstraint(constraint1);

                DetectorOrderingConstraint constraint2 = new DetectorOrderingConstraint(allocSelector, rtaSelector);
                //                constraint2.setSingleSource(allocSelector instanceof SingleDetectorFactorySelector);
                fakePlugin.addInterPassOrderingConstraint(constraint2);
            } else if (Config.getInstance().getStringProperty("callGraphMode").equals("XTA")) {
                DetectorFactory detectorFactory_allocationSites = new DetectorFactory(fakePlugin, BuildAllocationSites.class.getName(),
                        BuildAllocationSites.class, true, "slow", "", "");
                DetectorFactory detectorFactory_xta = new DetectorFactory(fakePlugin, BuildHybridTypeAnalysis.class.getName(),
                        BuildHybridTypeAnalysis.class, true, "slow", "", "");

                fakePlugin.addDetectorFactory(detectorFactory_allocationSites);
                fakePlugin.addDetectorFactory(detectorFactory_xta);

                dfc.registerDetector(detectorFactory_xta);
                dfc.registerDetector(detectorFactory_allocationSites);
                DetectorFactorySelector chaSelector = new SingleDetectorFactorySelector(fakePlugin, BuildInterproceduralCallGraph.class.getName());
                DetectorFactorySelector xtaSelector = new SingleDetectorFactorySelector(fakePlugin, BuildHybridTypeAnalysis.class.getName());
                DetectorFactorySelector allocSelector = new SingleDetectorFactorySelector(fakePlugin, BuildAllocationSites.class.getName());

                DetectorOrderingConstraint constraint1 = new DetectorOrderingConstraint(chaSelector, xtaSelector);
                //                constraint1.setSingleSource(chaSelector instanceof SingleDetectorFactorySelector);
                fakePlugin.addInterPassOrderingConstraint(constraint1);

                DetectorOrderingConstraint constraint2 = new DetectorOrderingConstraint(allocSelector, xtaSelector);
                //                constraint2.setSingleSource(allocSelector instanceof SingleDetectorFactorySelector);
                fakePlugin.addInterPassOrderingConstraint(constraint2);
            }


            if (!dfc.factoryIterator().hasNext() || fakePlugin.getDetectorFactories().isEmpty()) {
                throw new IllegalStateException();
            }

            engine.setDetectorFactoryCollection(dfc);

            engine.setUserPreferences(UserPreferences.createDefaultUserPreferences());



            engine.execute();

            //            if(Config.getInstance().getBooleanProperty("switch.enabled")) {
            //                InterproceduralCallGraph callGraph = null;
            //                //                if(mode == CallGraphAnalysisMode.CHA) {
            //                //                    callGraph = Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class);
            //                //                } else if (mode == CallGraphAnalysisMode.RTA) {
            //                //                    BuildRapidTypeAnalysis rtaAnalysis = new BuildRapidTypeAnalysis();
            //                //                    int lastDot = mainMethod.lastIndexOf(".");
            //                //
            //                //                    rtaAnalysis.runAnalysis(Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class),
            //                //                            XFactory.createXMethod(mainMethod.substring(0, lastDot), mainMethod.substring(lastDot+1), "([Ljava/lang/String)V", true));
            //                //                    callGraph = rtaAnalysis.getRtaGraph();
            //                //                }
            //                SwitchAspectsGenerator scg = new SwitchAspectsGenerator(callGraph);
            //                scg.generateAllSwitchAspects();
            //            }



        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            DetectorFactoryCollection.resetInstance(null);
        }
    }

}
