package edu.umd.cs.findbugs.detect;

import java.util.Set;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Method;

import edu.umass.cs.rfbi.cg.SwitchAspectsGenerator;
import edu.umass.cs.rfbi.util.Config;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.NonReportingDetector;
import edu.umd.cs.findbugs.SystemProperties;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.XClass;
import edu.umd.cs.findbugs.ba.XFactory;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraph;
import edu.umd.cs.findbugs.ba.ch.InterproceduralCallGraphVertex;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.ba.jsr305.Analysis;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.Global;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;

/**
 * Build the interprocedural call graph.
 *
 * NOTE: at the present time, this facility is only used to find relevant type
 * qualifiers. It could become a more general-purpose facility if there were a
 * need.
 *
 */
public class BuildInterproceduralCallGraph extends BytecodeScanningDetector implements NonReportingDetector {

    private InterproceduralCallGraph callGraph;

    private InterproceduralCallGraphVertex currentVertex;

    int count = 0;

    public static final boolean DEBUG = SystemProperties.getBoolean("rfbi.BuildInterproceduralCallGraph.debug");
    /**
     * Constructor.
     *
     * @param bugReporter
     *            the BugReporter to use
     */
    public BuildInterproceduralCallGraph(BugReporter bugReporter) {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        callGraph = new InterproceduralCallGraph();
    }

    @Override
    public void visitClassContext(ClassContext classContext) {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        super.visitClassContext(classContext);
    }

    @Override
    public void visitMethod(Method obj) {
        currentVertex = findVertex(getXMethod());
        super.visitMethod(obj);
    }

    @Override
    public void sawOpcode(int seen) {
        switch (seen) {
        case Constants.INVOKESTATIC:
        case Constants.INVOKEVIRTUAL:
        case Constants.INVOKEINTERFACE:
        case Constants.INVOKESPECIAL:
            count++;
            MethodDescriptor called = getMethodDescriptorOperand();
            if(currentVertex.getXmethod().getClassName().contains("HashMap") && currentVertex.getXmethod().getName().contains("hash")) {
                System.out.print("");//called.getName().contains("hashCode") &&
            }

            //            if(!calledXMethod.isAbstract()) {
            //                InterproceduralCallGraphVertex calledVertex = findVertex(calledXMethod);
            //                callGraph.createEdge(currentVertex, calledVertex);
            //            }

            addEdges4Subtypes(called, seen); // Kaituo
            break;
        default:
            break;
        }
    }

    /**
     * m
          ...
          invoke xxx T.m2

        ==>

        foreach t in T.getSubTypes():
            t.getMethod(m2).addPossibleCaller(m)

     * @author Kaituo
     */
    private void addEdges4Subtypes(MethodDescriptor called, int seen) {
        XMethod calledXMethod = XFactory.createXMethod(called);

        // Only invokevirtual and invokeinterface has dynamic calls (subtype is possible)
        if(seen==Constants.INVOKESTATIC || seen==Constants.INVOKESPECIAL) {
            InterproceduralCallGraphVertex calledVertex = findVertex(calledXMethod);
            callGraph.createEdge(currentVertex, calledVertex);

            return;
        }
        ClassDescriptor calledClass = called.getClassDescriptor();
        // if the class is an array like "[Ljava.lang.Object", then we are not interested to continue
        if(calledClass.isArray()) {
            return;
        }

        Subtypes2 subtypes2 = AnalysisContext.currentAnalysisContext().getSubtypes2();

        try {
            Set<ClassDescriptor> mySubtypes = subtypes2.getSubtypes(calledClass);

            for (ClassDescriptor c : mySubtypes) {
                if (c.equals(getClassDescriptor())) {
                    continue;
                }
                // the last argument is false because this method cannot be static
                MethodDescriptor called4Subtype = DescriptorFactory.instance().getMethodDescriptor(c.getClassName(), called.getName(),
                        called.getSignature(), false);
                XMethod called4SubtypeXMethod = XFactory.createXMethod(called4Subtype);

                // if a method is abstract, then this method can never be called directly
                if(called4SubtypeXMethod.isAbstract()) {
                    continue;
                }

                InterproceduralCallGraphVertex called4SubtypeVertex = findVertex(called4SubtypeXMethod);
                callGraph.createEdge(currentVertex, called4SubtypeVertex);
            }

            // if the method signature is concrete and the type of the method signature is abstract
            // and all subtypes override the method signature then the method signature can never be called
            // directly
            if(!calledXMethod.isAbstract()) {
                boolean allOverridden = true;
                for(ClassDescriptor c: mySubtypes) {
                    XClass clazz = AnalysisContext.currentXFactory().getXClass(c);
                    XMethod m = clazz.findMethod(called.getName(), called.getSignature(), called.isStatic());
                    if (m == null) {
                        allOverridden = false;
                        break;
                    }
                }
                if(allOverridden) {
                    InterproceduralCallGraphVertex calledVertex = findVertex(calledXMethod);
                    callGraph.createEdge(currentVertex, calledVertex);
                }
            }

        } catch (ClassNotFoundException e) {
            AnalysisContext.logError("Error while create call graph edges for " + called.toString());
            assert false;
        }

    }

    /**
     * Find the InterproceduralCallGraphVertex for given XMethod.
     *
     * @param xmethod
     *            an XMethod
     * @return the XMethod's InterproceduralCallGraphVertex
     */
    private InterproceduralCallGraphVertex findVertex(XMethod xmethod) {
        InterproceduralCallGraphVertex vertex;
        vertex = callGraph.lookupVertex(xmethod.getMethodDescriptor());
        if (vertex == null) {
            vertex = new InterproceduralCallGraphVertex();
            vertex.setXmethod(xmethod);
            callGraph.addVertex(vertex);
        }
        return vertex;
    }

    @Override
    public void report() {
        if (!Analysis.FIND_EFFECTIVE_RELEVANT_QUALIFIERS) {
            return;
        }
        Global.getAnalysisCache().eagerlyPutDatabase(InterproceduralCallGraph.class, callGraph);
        if(Config.getInstance().getBooleanProperty("switch.enabled")) {
            // we are in rta mode
            if(!Config.getInstance().getStringProperty("callGraphMode").equals("CHA")) {
                return;
            }
            SwitchAspectsGenerator scg = new SwitchAspectsGenerator(callGraph);
            scg.generateAllSwitchAspects();
            //TraceWriter.writeState(callGraph);
        }
    }
}
