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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;
import edu.umd.cs.findbugs.internalAnnotations.SlashedClassName;

/**
 * @author kaituo
 */
public class CallGraph {
    private final Map<String, Map<String, List<String>>> callgraphs;
    private static volatile CallGraph instance = null;

    /**
     * ToDo: add other patterns into the map if needed
     */
    private CallGraph() {
        callgraphs = new HashMap<>();
        callgraphs.put("HE", new HashMap<String, List<String>>()); // HE pattern
    }

    public static synchronized CallGraph getInstance() {
        if(instance==null) {
            instance = new CallGraph();
        }
        return instance;
    }

    public ArrayList<InterproceduralCallGraphVertex> getCallers(@SlashedClassName String
            className, String name, String signature, boolean isStatic) {
        InterproceduralCallGraph callGraph = Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class);
        InterproceduralCallGraphVertex vertex;
        MethodDescriptor called = DescriptorFactory.instance().getMethodDescriptor(className, name,
                signature, isStatic);

        vertex = callGraph.lookupVertex(called);
        if (vertex == null) {
            throw new RuntimeException("hashCode at least have some JDK callers");
        }
        Iterator<InterproceduralCallGraphVertex> itor = callGraph.predecessorIterator(vertex);
        ArrayList<InterproceduralCallGraphVertex> res = new ArrayList<>();
        while(itor.hasNext()) {
            InterproceduralCallGraphVertex caller = itor.next();
            res.add(caller);
            System.out.println(caller.getXmethod().toString());
        }
        return res;
    }

    /**
     * Check caller if it is an application class; if no, go upwards in the call graph and find its caller on the application side
     * This is actually an DFS.
     * @param caller: dotted class name, to be checked
     * @return null if no application side caller
     */
    void getApplicationCaller(final InterproceduralCallGraphVertex caller, final InterproceduralCallGraph callGraph,
            List<InterproceduralCallGraphVertex> res) {
        String type = caller.getXmethod().getClassName();
        if(type!=null && !AnalysisContext.currentAnalysisContext().isApplicationClass(type)) {
            Iterator<InterproceduralCallGraphVertex> itor = callGraph.predecessorIterator(caller);
            while(itor.hasNext()) {
                getApplicationCaller(itor.next(), callGraph, res);
                if(res.size()>0) {
                    return;
                }
            }
        }
        res.add(caller);
    }


}
