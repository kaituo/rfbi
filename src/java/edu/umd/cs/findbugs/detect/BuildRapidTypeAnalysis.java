package edu.umd.cs.findbugs.detect;

import static edu.umass.cs.rfbi.util.Assertions.check;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import edu.umass.cs.rfbi.cg.SwitchAspectsGenerator;
import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.NonReportingDetector;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.XClass;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphEdge;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.detect.BuildAllocationSites.AllocationSitesDatabase;

/**
 * @author kaituo
 *
 *         Adapted from
 *         https://github.com/EnSoftCorp/call-graph-toolbox/blob/master
 *         /com.ensoftcorp
 *         .open.cg/src/com/ensoftcorp/open/cg/analysis/RapidTypeAnalysis.java
 */
public class BuildRapidTypeAnalysis extends BytecodeScanningDetector implements NonReportingDetector {
    Map<XMethod, Set<ClassDescriptor>> allocationTypeSet = new HashMap<>();

    InterproceduralCallGraph rtaGraph = new InterproceduralCallGraph();

    public BuildRapidTypeAnalysis(BugReporter bugReporter) {

    }

    /**
     *
     * @param chaGraph
     *            : call graph from CHA
     * @param mainMethod
     *            : this algorithm would be given a single main method
     */
    public void runAnalysis(InterproceduralCallGraph chaGraph, LinkedList<XMethod> mainMethod) {

        // create a worklist and add the root method set
        LinkedList<XMethod> worklist = new LinkedList<>();

        worklist.addAll(mainMethod);

        AllocationSitesDatabase allocationSites = Global.getAnalysisCache().getDatabase(
                BuildAllocationSites.AllocationSitesDatabase.class);

        Subtypes2 subtypes2 = Global.getAnalysisCache().getDatabase(Subtypes2.class);

        while (!worklist.isEmpty()) {
            XMethod method = worklist.removeFirst();

            if(method.getClassName().contains("Object") && method.getName().contains("hashCode")) {
                System.out.print("");//called.getName().contains("hashCode") &&
            }

            // we should consider the allocation types instantiated directly in
            // the method
            Set<ClassDescriptor> allocationTypes = getAllocationTypesSet(method);

            if (allocationTypes.isEmpty()) {
                // allocations are contained (declared) within the methods in
                // the method reverse call graph
                // collect the types of each allocation
                allocationTypes.addAll(allocationSites.get(method));

                // we should also include the allocation types of each parent
                // method (in the current RTA call graph)
                InterproceduralCallGraphVertex vertex = rtaGraph.findVertex(method);
                check(vertex != null, "A vertex of this method should have been added: " + method);
                Iterator<InterproceduralCallGraphVertex> itor = rtaGraph.predecessorIterator(vertex);
                while (itor.hasNext()) {
                    InterproceduralCallGraphVertex parent = itor.next();
                    Set<ClassDescriptor> parentAllocationTypes = getAllocationTypesSet(parent.getXmethod());
                    allocationTypes.addAll(parentAllocationTypes);
                }
            }

            // next get a set of all the CHA call edges from the method and
            // create an RTA edge
            // from the method to the target method in the CHA call graph if the
            // target methods
            // type is compatible with the feasibly allocated types that would
            // reach this method
            InterproceduralCallGraphVertex current = chaGraph.lookupVertex(method.getMethodDescriptor());
            if(current == null) {
                continue;
            }
            Iterator<InterproceduralCallGraphEdge> i = chaGraph.outgoingEdgeIterator(current);
            while (i.hasNext()) {
                InterproceduralCallGraphEdge callEdge = i.next();
                XMethod calledMethod = callEdge.getTarget().getXmethod();
                if (calledMethod.isStatic() || "<init>".equals(calledMethod.getName())
                        || "<clinit>".equals(calledMethod.getName())) {
                    updateCallGraph(worklist, method, allocationTypes, callEdge, calledMethod);
                } else {
                    // the call edge is a dynamic dispatch, need to resolve
                    // possible dispatches
                    // a dispatch is possible if the type declaring the method
                    // is one of the
                    // allocated types (or the parent of an allocated type)
                    // note: we should consider the supertype hierarchy of the
                    // allocation types
                    // because methods can be inherited from parent types
                    //                    ClassDescriptor typeDeclaringCalledMethod = DescriptorFactory.instance().getClassDescriptor(
                    //                            calledMethod.getClassName());
                    if (subtypes2.getSupertypeQueryResults(allocationTypes).contains(calledMethod.getClassDescriptor())) {
                        updateCallGraph(worklist, method, allocationTypes, callEdge, calledMethod);
                    }
                }

            }

        }
    }

    /**
     * Updates the call graph and worklist for methods
     *
     * @param worklist
     * @param cgRTA
     * @param method
     * @param allocationTypes
     * @param callEdge
     * @param calledMethod
     */
    public void updateCallGraph(LinkedList<XMethod> worklist, XMethod method, Set<ClassDescriptor> allocationTypes,
            InterproceduralCallGraphEdge callEdge, XMethod calledMethod) {
        InterproceduralCallGraphVertex source = rtaGraph.findVertex(method);
        InterproceduralCallGraphVertex target = rtaGraph.findVertex(calledMethod);
        if (rtaGraph.lookupEdge(source, target) == null) {
            rtaGraph.createEdge(source, target);
            if (!worklist.contains(calledMethod)) {
                worklist.add(calledMethod);
            }
        } else {
            Set<ClassDescriptor> toAllocationTypes = getAllocationTypesSet(calledMethod);
            if (toAllocationTypes.addAll(allocationTypes)) {
                if (!worklist.contains(calledMethod)) {
                    worklist.add(calledMethod);
                }
            }
        }
    }

    private Set<ClassDescriptor> getAllocationTypesSet(XMethod meth) {
        if (allocationTypeSet.containsKey(meth)) {
            return allocationTypeSet.get(meth);
        } else {
            Set<ClassDescriptor> types = new HashSet<ClassDescriptor>();
            allocationTypeSet.put(meth, types);
            return types;
        }
    }

    /**
     * @return Returns the rtaGraph.
     */
    public InterproceduralCallGraph getRtaGraph() {
        return rtaGraph;
    }

    @Override
    public void report() {
        if (Config.getInstance().getBooleanProperty("switch.enabled")) {
            String mainMethod = Config.getInstance().getStringProperty("mainMethod");
            // we are in cha mode
            //            if (Config.getInstance().getStringProperty("mainMethod").isEmpty()) {
            //                return;
            //            }
            int lastDot = mainMethod.lastIndexOf(".");
            LinkedList<XMethod> mainMethods = new LinkedList<>();

            if(mainMethod.isEmpty()) {
                for(XClass from:  AnalysisContext.currentAnalysisContext().getSubtypes2().getXClassCollection()) {
                    for(XMethod m : from.getXMethods()) {
                        if(m.isPublic()) {
                            mainMethods.add(m);
                        }
                    }
                }
                runAnalysis(Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class), mainMethods);
            } else {
                mainMethods.add(XFactory.createXMethod(
                        mainMethod.substring(0, lastDot), mainMethod.substring(lastDot + 1), "([Ljava/lang/String;)V", true));
                runAnalysis(Global.getAnalysisCache().getDatabase(InterproceduralCallGraph.class), mainMethods);
            }
            SwitchAspectsGenerator scg = new SwitchAspectsGenerator(rtaGraph);
            scg.generateAllSwitchAspects();
            // TraceWriter.writeState(callGraph);
        }
    }
}
