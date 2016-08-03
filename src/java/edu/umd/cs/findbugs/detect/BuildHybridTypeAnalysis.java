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
import edu.umd.cs.findbugs.ba.ClassMember;
import edu.umd.cs.findbugs.ba.SignatureParser;
import edu.umd.cs.findbugs.ba.XClass;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XField;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphEdge;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.FieldOrMethodName;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.detect.BuildAllocationSites.AllocationSitesDatabase;
import edu.umd.cs.findbugs.detect.BuildAllocationSites.FieldReadDatabase;
import edu.umd.cs.findbugs.detect.BuildAllocationSites.FieldWriteDatabase;

/**
 * @author kaituo
 *
 *         Adapted from
 *         https://github.com/EnSoftCorp/call-graph-toolbox/blob/master
 *         /com.ensoftcorp
 *         .open.cg/src/com/ensoftcorp/open/cg/analysis/RapidTypeAnalysis.java
 */
public class BuildHybridTypeAnalysis extends BytecodeScanningDetector implements NonReportingDetector {
    Map<ClassMember, Set<ClassDescriptor>> allocationTypeSet = new HashMap<>();

    InterproceduralCallGraph xtaGraph = new InterproceduralCallGraph();

    AllocationSitesDatabase allocationSites = Global.getAnalysisCache().getDatabase(
            BuildAllocationSites.AllocationSitesDatabase.class);
    FieldWriteDatabase writeFieldsDB = Global.getAnalysisCache().getDatabase(
            BuildAllocationSites.FieldWriteDatabase.class);
    FieldReadDatabase readFieldsDB = Global.getAnalysisCache().getDatabase(
            BuildAllocationSites.FieldReadDatabase.class);

    Subtypes2 subtypes2 = Global.getAnalysisCache().getDatabase(Subtypes2.class);


    public BuildHybridTypeAnalysis(BugReporter bugReporter) {

    }

    /**
     *
     * @param chaGraph
     *            : call graph from CHA
     * @param mainMethod
     *            : this algorithm would be given a single main method
     * @throws ClassNotFoundException
     */
    public void runAnalysis(InterproceduralCallGraph chaGraph, LinkedList<XMethod> mainMethod) throws ClassNotFoundException {
        // create a worklist and add the root method set
        LinkedList<ClassMember> worklist = new LinkedList<>();

        worklist.addAll(mainMethod);

        while (!worklist.isEmpty()) {
            FieldOrMethodName workitem = worklist.removeFirst();

            if(workitem instanceof XMethod) {
                XMethod method = (XMethod)workitem;
                if(method.getName().contains("addTargeter")) {
                    System.out.print("");
                }
                // we should consider the allocation types instantiated directly in
                // the method
                Set<ClassDescriptor> allocationTypes = getAllocationTypesSet(method);

                if (allocationTypes.isEmpty()) {
                    // we should consider the allocation types instantiated directly in the method
                    // note even if the allocation set is not empty here, this may be the first time
                    // we've reached this method because information could have been propagated from
                    // a field first
                    allocationTypes.addAll(allocationSites.get(method));

                    // we should also include the allocation types of each parent
                    // method (in the current XTA call graph)

                    // restrict allocation types declared in parents to only the types that are compatible
                    // with the type or subtype of each of the method's parameters
                    Set<ClassDescriptor> parameterTypes = new HashSet<>();
                    SignatureParser sigParser = new SignatureParser(method.getSignature());
                    Iterator<String> parameterIterator = sigParser.parameterSignatureIterator();
                    while(parameterIterator.hasNext()) {
                        String parameterSignature = parameterIterator.next();
                        // we don't deal with array and primitive types
                        if(!parameterSignature.startsWith("L")) {
                            continue;
                        }
                        parameterTypes.add(DescriptorFactory.createClassDescriptorFromSignature(parameterSignature));
                    }

                    Set<ClassDescriptor> parameterTypeHierarchy = subtypes2.getSubtypes(parameterTypes);

                    InterproceduralCallGraphVertex vertex = xtaGraph.findVertex(method);
                    check(vertex != null, "A vertex of this method should have been added: " + method);
                    Iterator<InterproceduralCallGraphVertex> xtaParentsItor = xtaGraph.predecessorIterator(vertex);
                    while (xtaParentsItor.hasNext()) {
                        InterproceduralCallGraphVertex parent = xtaParentsItor.next();
                        Set<ClassDescriptor> parentAllocationTypes = getAllocationTypesSet(parent.getXmethod());
                        parentAllocationTypes.retainAll(parameterTypeHierarchy);
                        allocationTypes.addAll(parentAllocationTypes);
                    }

                    // MTA also considers the return types of methods that are called from the given method
                    // add allocations that are made by calling a method (static or virtual) that return an allocation
                    // note that the declared return type does not involve resolving dynamic dispatches (so this could be the
                    // return type of any method resolved by a CHA analysis since all are statically typed to the same type)
                    Iterator<InterproceduralCallGraphVertex> chaChildrenItor = chaGraph.successorIterator(vertex);
                    while (chaChildrenItor.hasNext()) {
                        SignatureParser sp = new SignatureParser(chaChildrenItor.next().getXmethod().getSignature());
                        String returnSignature = sp.getReturnTypeSignature();
                        // this is an array that contains no method

                        if(!returnSignature.startsWith("L")) {
                            continue;
                        }
                        // returnSignature, e.g. "Ljava/lang/Boolean;"
                        allocationTypes.add(DescriptorFactory.instance().getClassDescriptor(returnSignature.substring(1, returnSignature.length()-1)));
                    }

                    // In FTA any method in the method or method's parents that reads from a field
                    // can have a reference to the allocations that occur in any another method that writes to that field
                    Set<XField> readFields = new HashSet<>();
                    readFields.addAll(readFieldsDB.get(method));

                    xtaParentsItor = xtaGraph.predecessorIterator(vertex);

                    while(xtaParentsItor.hasNext()) {
                        InterproceduralCallGraphVertex parent = xtaParentsItor.next();
                        readFields.addAll(readFieldsDB.get(parent.getXmethod()));
                    }

                    for(XField readField: readFields) {
                        Set<ClassDescriptor> fieldAllocationTypes = getAllocationTypesSet(readField);
                        allocationTypes.addAll(fieldAllocationTypes);
                    }

                    // In FTA if the method writes to a field then all the compatible allocated types available to the method
                    // can be propagated to the field
                    Set<XField> writtenFields = new HashSet<>();
                    writtenFields.addAll(writeFieldsDB.get(method));

                    xtaParentsItor = xtaGraph.predecessorIterator(vertex);

                    while(xtaParentsItor.hasNext()) {
                        InterproceduralCallGraphVertex parent = xtaParentsItor.next();
                        writtenFields.addAll(writeFieldsDB.get(parent.getXmethod()));
                    }

                    for(XField writtenField: writtenFields) {
                        Set<ClassDescriptor> fieldAllocationTypes = getAllocationTypesSet(writtenField);
                        String fieldSignature = writtenField.getSignature();

                        if (!fieldSignature.startsWith("L")) {
                            continue;
                        }
                        ClassDescriptor writtenFieldType = DescriptorFactory.createClassDescriptorFromSignature(fieldSignature);

                        Set<ClassDescriptor> compatibleTypes = subtypes2.getSubtypes(writtenFieldType);
                        compatibleTypes.retainAll(allocationTypes);
                        if(fieldAllocationTypes.addAll(compatibleTypes)) {
                            if(!worklist.contains(writtenField)){
                                worklist.add(writtenField);
                            }
                        }
                    }
                }

                // TODO: ETA: exception type analysis

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
            else {
                // new allocation types were propagated to a field, which means methods that read from the field may get new allocation types
                XField field = (XField)workitem;
                Set<ClassDescriptor> fieldAllocationTypes = getAllocationTypesSet(field);
                Set<XMethod> readingMethods = readFieldsDB.get(field);
                for(XMethod readingMethod: readingMethods) {
                    Set<ClassDescriptor> readingMethodAllocationTypes = getAllocationTypesSet(readingMethod);
                    if(readingMethodAllocationTypes.addAll(fieldAllocationTypes)){
                        if(!worklist.contains(readingMethod)){
                            worklist.add(readingMethod);
                        }
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
     * @throws ClassNotFoundException
     */
    public void updateCallGraph(LinkedList<ClassMember> worklist, XMethod method, Set<ClassDescriptor> allocationTypes,
            InterproceduralCallGraphEdge callEdge, XMethod calledMethod) throws ClassNotFoundException {
        InterproceduralCallGraphVertex source = xtaGraph.findVertex(method);
        InterproceduralCallGraphVertex target = xtaGraph.findVertex(calledMethod);
        if (xtaGraph.lookupEdge(source, target) == null) {
            xtaGraph.createEdge(source, target);
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

        Set<XField> writtenFields = new HashSet<>();
        writtenFields.addAll(writeFieldsDB.get(method));

        for(XField writtenField: writtenFields) {
            Set<ClassDescriptor> fieldAllocationTypes = getAllocationTypesSet(writtenField);
            String fieldSignature = writtenField.getSignature();

            if (!fieldSignature.startsWith("L")) {
                continue;
            }
            ClassDescriptor writtenFieldType = DescriptorFactory.createClassDescriptorFromSignature(fieldSignature);
            Set<ClassDescriptor> compatibleTypes = subtypes2.getSubtypes(writtenFieldType);
            compatibleTypes.retainAll(allocationTypes);
            if(fieldAllocationTypes.addAll(compatibleTypes)) {
                if(!worklist.contains(writtenField)){
                    worklist.add(writtenField);
                }
            }
        }
    }

    private Set<ClassDescriptor> getAllocationTypesSet(ClassMember meth) {
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
    public InterproceduralCallGraph getXtaGraph() {
        return xtaGraph;
    }

    @Override
    public void report() {
        if (Config.getInstance().getBooleanProperty("switch.enabled")) {
            String mainMethod = Config.getInstance().getStringProperty("mainMethod");

            int lastDot = mainMethod.lastIndexOf(".");
            LinkedList<XMethod> mainMethods = new LinkedList<>();

            try {
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
                SwitchAspectsGenerator scg = new SwitchAspectsGenerator(xtaGraph);
                scg.generateAllSwitchAspects();
                // TraceWriter.writeState(callGraph);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
