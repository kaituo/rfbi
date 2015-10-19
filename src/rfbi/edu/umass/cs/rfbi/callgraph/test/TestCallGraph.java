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

package edu.umass.cs.rfbi.callgraph.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author kaituo
 */
public class TestCallGraph {
    static class Vertex {
        String type;
        boolean isApplicationClass;

        public Vertex(String t, boolean isApplied, AContext aContext) {
            type = t;
            isApplicationClass = isApplied;
            aContext.setApplicationClass(type, isApplicationClass);
        }
    }

    static class InterCallGraph {
        Map<Vertex, List<Vertex>> graph;
        public InterCallGraph(Map<Vertex, List<Vertex>> g) {
            graph = g;

        }

        Iterator<Vertex> predecessorIterator(Vertex callee) {
            if(graph.containsKey(callee)) {
                return graph.get(callee).iterator();
            } else {
                return null;
            }
        }
    }

    static class AContext {
        // private static volatile AContext instance = null;
        private final AContext instance = null;
        Map<String, Boolean> name2vertex;
        private AContext() {
            name2vertex = new HashMap<String, Boolean>();
        }

        //        public static synchronized AContext getInstance() {
        //            if(instance==null) {
        //                instance = new AContext();
        //            }
        //            return instance;
        //        }

        boolean isApplicationClass(String clasName) {
            return name2vertex.get(clasName);
        }

        void setApplicationClass(String clasName, boolean isApplication) {
            name2vertex.put(clasName, isApplication);
        }

    }


    void getApplicationCaller(final Vertex caller, final InterCallGraph callGraph,
            List<Vertex> res, AContext acontext) {
        String type = caller.type;
        if(type==null) {
            throw new NullPointerException("An xmethod has not class name.  This should not happen.");
        }
        if(!acontext.isApplicationClass(type)) {
            Iterator<Vertex> itor = callGraph.predecessorIterator(caller);
            while(itor!=null && itor.hasNext()) {
                getApplicationCaller(itor.next(), callGraph, res, acontext);
                if(res.size()>0) {
                    return;
                }
            }
        } else {
            res.add(caller);
        }
    }

    /**
     * A => AB
     */
    @Test
    public void testGetApplicationCaller1() {
        //AContext context = AContext.getInstance();
        AContext context = new AContext();

        Vertex v1 = new Vertex("A", false, context);
        Vertex v2 = new Vertex("AB", true, context);

        Map<Vertex, List<Vertex>> map = new HashMap<>();
        List<Vertex> listA = new ArrayList<>();
        listA.add(v2);
        map.put(v1, listA);
        InterCallGraph icg = new InterCallGraph(map);

        List<Vertex> res = new ArrayList<Vertex>();
        getApplicationCaller(v1, icg, res, context);
        assertEquals(res.size(), 1);
        assertEquals(res.get(0).type, "AB");
    }

    /**
     * A => AB,AC (=> means "called by", AB is an application class while AC is not).
     */
    @Test
    public void testGetApplicationCaller2() {
        //AContext context = AContext.getInstance();
        AContext context = new AContext();

        Vertex v1 = new Vertex("A", false, context);
        Vertex v2 = new Vertex("AA", false, context);
        Vertex v3 = new Vertex("AB", true, context);

        Map<Vertex, List<Vertex>> map = new HashMap<>();
        List<Vertex> listA = new ArrayList<>();
        listA.add(v2);
        listA.add(v3);

        map.put(v1, listA);
        InterCallGraph icg = new InterCallGraph(map);

        List<Vertex> res = new ArrayList<Vertex>();
        getApplicationCaller(v1, icg, res, context);
        assertEquals(res.size(), 1);
        assertEquals(res.get(0).type, "AB");
    }

    /**
     * AB and ABA are application classes while AC is not.
     * A => AB,AC
     * AB => ABA
     */
    @Test
    public void testGetApplicationCaller3() {
        //AContext context = AContext.getInstance();
        AContext context = new AContext();

        Vertex v1 = new Vertex("A", false, context);
        Vertex v2 = new Vertex("AA", false, context);
        Vertex v3 = new Vertex("AB", true, context);
        Vertex v4 = new Vertex("ABA", true, context);

        Map<Vertex, List<Vertex>> map = new HashMap<>();
        List<Vertex> listA = new ArrayList<>();
        listA.add(v2);
        listA.add(v3);

        List<Vertex> listAB = new ArrayList<>();
        listAB.add(v4);

        map.put(v1, listA);
        map.put(v3, listAB);

        InterCallGraph icg = new InterCallGraph(map);

        List<Vertex> res = new ArrayList<Vertex>();
        getApplicationCaller(v1, icg, res, context);
        assertEquals(res.size(), 1);
        assertEquals(res.get(0).type, "AB");
    }

    /**
     * ABA are application classes while AC and AB are not.
     * A => AB,AC
     * AB => ABA
     */
    @Test
    public void testGetApplicationCaller4() {
        //AContext context = AContext.getInstance();
        AContext context = new AContext();

        Vertex v1 = new Vertex("A", false, context);
        Vertex v2 = new Vertex("AA", false, context);
        Vertex v3 = new Vertex("AB", false, context);
        Vertex v4 = new Vertex("ABA", true, context);

        Map<Vertex, List<Vertex>> map = new HashMap<>();
        List<Vertex> listA = new ArrayList<>();
        listA.add(v2);
        listA.add(v3);

        List<Vertex> listAB = new ArrayList<>();
        listAB.add(v4);

        map.put(v1, listA);
        map.put(v3, listAB);

        InterCallGraph icg = new InterCallGraph(map);

        List<Vertex> res = new ArrayList<Vertex>();
        getApplicationCaller(v1, icg, res, context);
        assertEquals(res.size(), 1);
        assertEquals(res.get(0).type, "ABA");
    }


}
