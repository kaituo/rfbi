package edu.umd.cs.findbugs.detect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.NonReportingDetector;
import edu.umd.cs.findbugs.ba.XField;
import edu.umd.cs.findbugs.ba.XMethod;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.Global;

/**
 * @author kaituo
 */
public class BuildAllocationSites extends BytecodeScanningDetector implements NonReportingDetector {
    private final AllocationSitesDatabase allocationSites = new AllocationSitesDatabase();
    private final FieldReadDatabase readFields = new FieldReadDatabase();
    private final FieldWriteDatabase writeFields = new FieldWriteDatabase();


    public static class AllocationSitesDatabase {
        Map<XMethod, Set<ClassDescriptor>> allocationSitesdb = new HashMap<>();

        public void add(XMethod meth, ClassDescriptor clsName) {
            if(!allocationSitesdb.containsKey(meth)) {
                allocationSitesdb.put(meth, new HashSet<ClassDescriptor>());
            }
            // add sth like "java/lang/String"
            allocationSitesdb.get(meth).add(clsName);
        }

        public Set<ClassDescriptor> get(XMethod meth) {
            if(!allocationSitesdb.containsKey(meth)) {
                allocationSitesdb.put(meth, new HashSet<ClassDescriptor>());
            }
            // add sth like "java/lang/String"
            return allocationSitesdb.get(meth);
        }
    }

    public static class FieldReadDatabase {
        Map<XMethod, Set<XField>> fieldReaddb = new HashMap<>();
        Map<XField, Set<XMethod>> fieldReadReversedb = new HashMap<>();

        public void add(XMethod meth, XField clsName) {
            if(!fieldReaddb.containsKey(meth)) {
                fieldReaddb.put(meth, new HashSet<XField>());
            }
            if(!fieldReadReversedb.containsKey(clsName)) {
                fieldReadReversedb.put(clsName, new HashSet<XMethod>());
            }
            // add sth like "java/lang/String"
            fieldReaddb.get(meth).add(clsName);
            fieldReadReversedb.get(clsName).add(meth);
        }

        public Set<XField> get(XMethod meth) {
            if(!fieldReaddb.containsKey(meth)) {
                fieldReaddb.put(meth, new HashSet<XField>());
            }
            // add sth like "java/lang/String"
            return fieldReaddb.get(meth);
        }

        public Set<XMethod> get(XField field) {
            if(!fieldReadReversedb.containsKey(field)) {
                fieldReadReversedb.put(field, new HashSet<XMethod>());
            }
            // add sth like "java/lang/String"
            return fieldReadReversedb.get(field);
        }
    }

    public static class FieldWriteDatabase {
        Map<XMethod, Set<XField>> fieldWritedb = new HashMap<>();

        public void add(XMethod meth, XField clsName) {
            if(!fieldWritedb.containsKey(meth)) {
                fieldWritedb.put(meth, new HashSet<XField>());
            }
            // add sth like "java/lang/String"
            fieldWritedb.get(meth).add(clsName);
        }

        public Set<XField> get(XMethod meth) {
            if(!fieldWritedb.containsKey(meth)) {
                fieldWritedb.put(meth, new HashSet<XField>());
            }
            // add sth like "java/lang/String"
            return fieldWritedb.get(meth);
        }
    }

    /**
     * @param bugReporter
     */
    public BuildAllocationSites(BugReporter bugReporter) {
    }

    @Override
    public void sawOpcode(int seen) {
        if(getXMethod().getName().contains("addTargeter")) {
            System.out.print("");
        }
        if ((seen == INVOKESPECIAL) && "<init>".equals(getNameConstantOperand())) {
            allocationSites.add(getXMethod(), DescriptorFactory.instance().getClassDescriptor(getClassConstantOperand()));
        } else if ((seen == PUTFIELD || seen == PUTSTATIC)) {
            writeFields.add(getXMethod(),  getXFieldOperand());
        } else if (seen == GETSTATIC || seen == GETFIELD) {
            readFields.add(getXMethod(),  getXFieldOperand());
        }
    }

    @Override
    public void report() {
        Global.getAnalysisCache().eagerlyPutDatabase(AllocationSitesDatabase.class, allocationSites);
        Global.getAnalysisCache().eagerlyPutDatabase(FieldWriteDatabase.class, writeFields);
        Global.getAnalysisCache().eagerlyPutDatabase(FieldReadDatabase.class, readFields);
    }

}
