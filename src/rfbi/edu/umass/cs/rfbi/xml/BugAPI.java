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

package edu.umass.cs.rfbi.xml;

/**
 * @author Christoph Reichenbach & Kaituo
 */
import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.BugAnnotation;
import edu.umd.cs.findbugs.BugAnnotationVisitor;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.ClassAnnotation;
import edu.umd.cs.findbugs.FieldAnnotation;
import edu.umd.cs.findbugs.IntAnnotation;
import edu.umd.cs.findbugs.LocalVariableAnnotation;
import edu.umd.cs.findbugs.MethodAnnotation;
import edu.umd.cs.findbugs.SortedBugCollection;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import edu.umd.cs.findbugs.StringAnnotation;
import edu.umd.cs.findbugs.TypeAnnotation;

/**
 * Helper class for the FindBugs XML API.
 *
 * This class provides static operations for all the functionality we need.
 * Most of these operations are trivial wrappers; arguably, you can think
 * of much of this class as more documentation than actual utility.
 *
 *  Note  that  to  run  this,  you  have  to  make  sure  to  include
 * `findbugs.jar' on your classpath with directory annotations: if you
 * write `-classpath findbugs.jar', the program will crash; write
 * `-classpath ./findbugs.jar' instead.
 *
 */
public class BugAPI
{
    //	public static boolean deleteSourceL(BugInstance bug, String classN, String methodN, int sourceL) {
    //		return bug.deleteSourceLineAnnotation(classN, methodN, sourceL);
    //	}
    //
    //	public static boolean deleteDE(BugInstance bug, String classN, String methodN, String methS, int l) {
    //		return bug.deleteDEAnnotation(classN, methodN, methS, l);
    //	}

    public static Iterable<BugInstance>
    getAllBugs(SortedBugCollection bugs)
    {
        return bugs;
    }

    public static Iterable<BugInstance>
    getBugsByType(SortedBugCollection bugs, String type)
    {
        return BugFilterIterator.byType(bugs, type);
    }

    public static void
    storeBugs(SortedBugCollection bugs, String filename)
    {
        try {
            bugs.writeXML(filename);
        } catch (Exception exn) {
            exn.printStackTrace(System.err);
        }
    }

    public static SortedBugCollection
    loadBugs(String filename)
    {
        try {
            SortedBugCollection bugs = new SortedBugCollection();
            bugs.readXML(filename);
            return bugs;
        } catch (Exception exn) {
            exn.printStackTrace(System.err);
            return null;
        }
    }

    public static void
    minimisePriority(BugInstance bug)
    {
        bug.lowerPriorityALot();
    }

    public static Set<String>
    getClassNames(BugInstance bug)
    {
        final Set<String> results = new HashSet<String>();
        BugAnnotationVisitor v = new DefaultBugAnnotationVisitor() {
            @Override
            public void
            visitClassAnnotation(ClassAnnotation ca)
            {
                results.add(ca.getClassName());
            }
        };
        for (BugAnnotation annot : bug.getAnnotations()) {
            annot.accept(v);
        }
        return results;
    }

    public static Set<String>
    getClassNameZero(BugInstance bug)
    {
        final Set<String> results = new HashSet<String>();
        BugAnnotationVisitor v = new DefaultBugAnnotationVisitor() {
            @Override
            public void
            visitClassAnnotation(ClassAnnotation ca)
            {
                results.add(ca.getClassName());
            }
        };
        BugAnnotation annot = bug.getAnnotations().get(0);
        annot.accept(v);
        return results;
    }

    public static Set<MethodAnnotation>
    getMethod(BugInstance bug)
    {
        final Set<MethodAnnotation> results = new HashSet<MethodAnnotation>();
        BugAnnotationVisitor v = new DefaultBugAnnotationVisitor() {
            @Override
            public void
            visitMethodAnnotation(MethodAnnotation methodAnnotation)
            {
                results.add(methodAnnotation);
            }
        };
        for (BugAnnotation annot : bug.getAnnotations()) {
            annot.accept(v);
        }
        return results;
    }

    public static Set<SourceLineAnnotation>
    getSourceLines(BugInstance bug)
    {
        final Set<SourceLineAnnotation> results = new HashSet<SourceLineAnnotation>();
        BugAnnotationVisitor v = new DefaultBugAnnotationVisitor() {
            @Override
            public void
            visitSourceLineAnnotation(SourceLineAnnotation sl)
            {
                results.add(sl);
            }
        };
        for (BugAnnotation annot : bug.getAnnotations()) {
            annot.accept(v);
        }
        return results;
    }

    private static class
    DefaultBugAnnotationVisitor implements BugAnnotationVisitor
    {
        @Override
        public void visitClassAnnotation(ClassAnnotation classAnnotation) {};
        @Override
        public void	visitFieldAnnotation(FieldAnnotation fieldAnnotation)  {};
        @Override
        public void	visitIntAnnotation(IntAnnotation intAnnotation)  {};
        @Override
        public  void	visitLocalVariableAnnotation(LocalVariableAnnotation localVariableAnnotation)  {};
        @Override
        public  void	visitMethodAnnotation(MethodAnnotation methodAnnotation)  {};
        @Override
        public  void	visitSourceLineAnnotation(SourceLineAnnotation sourceLineAnnotation)  {};
        @Override
        public  void	visitStringAnnotation(StringAnnotation stringAnnotation)  {};
        @Override
        public  void	visitTypeAnnotation(TypeAnnotation typeAnnotation)  {};
    }
}


