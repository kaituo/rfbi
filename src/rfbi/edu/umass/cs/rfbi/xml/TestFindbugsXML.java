package edu.umass.cs.rfbi.xml;

/**
 * @author Kaituo
 */
import java.util.Set;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.MethodAnnotation;
import edu.umd.cs.findbugs.SortedBugCollection;
import edu.umd.cs.findbugs.SourceLineAnnotation;

/**
 * Test for the internal FindBugs XML API.
 *
 *  Note  that  to  run  this,  you  have  to  make  sure  to  include
 * `findbugs.jar' on your classpath with directory annotations: if you
 * write `-classpath findbugs.jar', the program will crash; write
 * `-classpath ./findbugs.jar' instead.
 *
 * http://findbugs.sourceforge.net/api/edu/umd/cs/findbugs/SortedBugCollection.html
 *
 * This class tests the FindBugs XML API.
 */
public class TestFindbugsXML
{
    public static final void
    main(String[] args) throws java.io.IOException, org.dom4j.DocumentException
    {
        // When the class runs, current path directory is project root directory
        // Use relative path to specify resource location
        SortedBugCollection bugs = BugAPI.loadBugs("./src/rfbi/resources/BCEL.xml");

        for (BugInstance bi : BugAPI.getBugsByType(bugs, "RR_NOT_CHECKED")) {
            System.err.println(" * " + bi);
            for (String s : BugAPI.getClassNames(bi)) {
                System.err.println("  + " + s);
            }
        }

        for (BugInstance bi : BugAPI.getBugsByType(bugs, "RR_NOT_CHECKED")) {
            System.err.println(" * " + bi);

            // NOTE:  The same BugInstance may point to multiple locations in the same file
            for (SourceLineAnnotation s : BugAPI.getSourceLines(bi)) {
                System.err.println("  + " + s.getSourcePath() + " " + s.getSourceFile() + ": L" + s.getStartLine() + "-" + s.getEndLine());
            }

            for (MethodAnnotation s : BugAPI.getMethod(bi)) {
                System.err.println("  + " + s.getClassName() + " " + s.getMethodName());
            }

            Set<String> cN = BugAPI.getClassNames(bi);
            String[] array = cN.toArray(new String[cN.size()]);
            System.err.println("  + " + array[0]);

        }

        for (BugInstance bi : BugAPI.getBugsByType(bugs, "DE_MIGHT_IGNORE")) {
            System.err.println(" * " + bi);
            for (String s : BugAPI.getClassNames(bi)) {
                System.err.println("  + " + s);
            }
        }

        for (BugInstance bi : BugAPI.getBugsByType(bugs, "EQ_OVERRIDING_EQUALS_NOT_SYMMETRIC")) {
            BugAPI.minimisePriority(bi);
        }

        BugAPI.storeBugs(bugs, "./src/rfbi/resources/BCEL_modified.xml");
    }
}


