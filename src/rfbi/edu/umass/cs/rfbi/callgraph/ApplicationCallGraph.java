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

package edu.umass.cs.rfbi.callgraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.umd.cs.findbugs.SystemProperties;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;
import edu.umd.cs.findbugs.internalAnnotations.SlashedClassName;

/**
 * @author kaituo
 */
public class ApplicationCallGraph {

    //    static class CallGraphDepth {
    //        int distance;
    //        private CallGraphDepth() {
    //            distance = 0;
    //        }
    //
    //        CallGraphDepth incrementDepth() {
    //            distance++;
    //            return this;
    //        }
    //    }

    // put -Drfbi.callgraph.debug=true as VM arguments, you can print out debug info
    public static final boolean DEBUG = SystemProperties.getBoolean("rfbi.callgraph.debug");

    private static volatile ApplicationCallGraph instance = null;
    //private final int defaultSearchDepth = 20;

    /**
     * ToDo: add other patterns into the map if needed
     */
    private ApplicationCallGraph() {}

    public static synchronized ApplicationCallGraph getInstance() {
        if(instance==null) {
            instance = new ApplicationCallGraph();
        }
        return instance;
    }

    /**
     * Get callers of a method; these callers are all application class. The difference between getCallers
     * and getApplicationCallers is that the former is a list of direct callers we want to collect while the latter
     * is sth we want to explore further if one of the direct callers is not an application class.
     *
     * @param className
     * @param name: method name
     * @param signature: method signature
     * @param isStatic
     * @return
     */
    public Set<InterproceduralCallGraphVertex> getCallers(@SlashedClassName String
            className, String name, String signature, boolean isStatic) {
        InterproceduralCallGraph callGraph = Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class);
        InterproceduralCallGraphVertex vertex;
        MethodDescriptor called = DescriptorFactory.instance().getMethodDescriptor(className, name,
                signature, isStatic);

        vertex = callGraph.lookupVertex(called);
        if (vertex == null) {
            throw new RuntimeException("Cannot find a caller for " + called.toString());
        }
        Iterator<InterproceduralCallGraphVertex> itor = callGraph.predecessorIterator(vertex);
        Set<InterproceduralCallGraphVertex> res = new HashSet<>();
        HashSet<InterproceduralCallGraphVertex> cache = new HashSet<>();
        int count = 0;
        while(itor.hasNext()) {
            InterproceduralCallGraphVertex caller = itor.next();
            Set<InterproceduralCallGraphVertex> iterRes = new HashSet<>();

            getApplicationCallers(caller, callGraph, iterRes, cache);
            res.addAll(iterRes);
            count++;
        }

        if(DEBUG) {
            System.out.println("The size of the caller: " + count);
            /*for(InterproceduralCallGraphVertex v: res) {
                System.out.println(v.getXmethod().toString());
            }*/
        }

        return res;
    }

    boolean isUndesiredMethName(String methName) {
        // <init> method can have arguments too.  THey are usually associated with invokespecial
        // or "new constructor" call.  The receiver here is not fully constructed so not very
        // useful for us. So we continue to search upwards in the call graph. Similarly for <clinit>
        if(methName.contains("$") || methName.contains("<init>") || methName.contains("<clinit>")) {
            return true;
        }

        return false;
    }

    /**
     * Check if 1) caller belongs to an application class; 2) caller has at least one argument (not this);
     * 3) caller does not belong to an inner class and is not aspect generated method (these are covered by checking
     * if the class name or method name contains $. If no, go upwards in the call graph and find its caller on the application side
     * This is actually an DFS.  Compared with getCallers, this method should be used more often.
     * @param caller: dotted class name, to be checked
     * @return null if no application side caller
     */
    void getApplicationCallers(final InterproceduralCallGraphVertex caller, final InterproceduralCallGraph callGraph,
            Set<InterproceduralCallGraphVertex> res, /*CallGraphDepth depth, */ Set<InterproceduralCallGraphVertex> visited) {
        //        if(depth.distance > defaultSearchDepth) {
        //            return;
        //        }
        XMethod callerMeth = caller.getXmethod();
        String type = callerMeth.getClassName();

        if(type==null) {
            throw new NullPointerException("An xmethod has not class name.  This should not happen.");
        }
        if(callerMeth.getNumParams()<1 || !AnalysisContext.currentAnalysisContext().isApplicationClass(type)
                || type.contains("$") || isUndesiredMethName(callerMeth.getName())) {

            Iterator<InterproceduralCallGraphVertex> itor = callGraph.predecessorIterator(caller);
            assert itor!=null; // itor cannot be null even if itor.hasNext() returns false.
            while(itor.hasNext()) {

                InterproceduralCallGraphVertex newCaller = itor.next();
                //RFile.writeDE2(newCaller.getXmethod().toString(), "/home/kaituo/tmp/a");
                if (!visited.contains(newCaller)) {
                    visited.add(newCaller);
                    getApplicationCallers(newCaller, callGraph, res, visited);
                }
                if(res.size()>0) {
                    return;
                }
            }
        } else {
            res.add(caller);
        }
    }


}
